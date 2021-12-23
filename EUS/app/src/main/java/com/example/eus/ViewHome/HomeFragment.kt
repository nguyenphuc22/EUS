package com.example.eus.ViewHome

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eus.ODT.Account
import com.example.eus.ODT.Product
import com.example.eus.R
import com.example.eus.SharePref.ManagerSharePref
import com.example.eus.ViewModel.EUSViewModel
import com.example.eus.databinding.FragmentHomeBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.core.content.ContextCompat.getSystemService

import android.app.SearchManager
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import androidx.core.view.isInvisible


class HomeFragment : Fragment(), OnClickItemCategory, OnClickItemProduct{

    private lateinit var binding : FragmentHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var adapterCategory: AdapterCategory
    private lateinit var adapterProduct: AdapterProduct
    private lateinit var viewModel : EUSViewModel
    private lateinit var sharedPref:ManagerSharePref
    private lateinit var txtTv :TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(EUSViewModel::class.java)
        auth= FirebaseAuth.getInstance()
        sharedPref= ManagerSharePref()
        if(auth.currentUser!=null){
            sharedPref.setAccount(activity,auth.currentUser?.email.toString().replace(".",""))
            var account = Account.Builder()
                .addUsername(auth.currentUser?.email.toString().replace(".",""))
                .addEmail(auth.currentUser?.email.toString())
                .build()
            viewModel.isExist(account).observe(viewLifecycleOwner, {
                Log.i("test123",it.toString())

                if(it==false){
                    viewModel.register(account)
                }
            })
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterCategory = AdapterCategory()
        adapterCategory.addOnClickCategory(this)
        viewModel.getCategory()?.observe(viewLifecycleOwner, Observer {
            adapterCategory.setData(it)
        })
        binding.recyclerCategory.layoutManager = GridLayoutManager(context,5)
        binding.recyclerCategory.adapter = adapterCategory

        adapterProduct = AdapterProduct()
        adapterProduct.addOnClickItem(this)
        viewModel.getProduct()?.observe(viewLifecycleOwner, Observer {

            adapterProduct.setProduct( it )



        })

        binding.recyclerListProduct.layoutManager = GridLayoutManager(context,2)
        binding.recyclerListProduct.adapter = adapterProduct

        Log.i("well123", " this worked 1 " + sharedPref.getAccount(activity))

//        viewModel.setUser(sharedPref.getAccount(activity).toString(), Util.fakeAccount())
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId) {
            R.id.itemProfile -> {
                if(sharedPref.getState(activity).equals("isLogged")){
                    this.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                }else{
                    this.findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                }
            }
            R.id.itemCart->{
                this.findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
            }
            R.id.itemSearch->{

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val badgeLayout = menu.findItem(R.id.itemCart).actionView as RelativeLayout
        txtTv = badgeLayout.findViewById<View>(R.id.count) as TextView
        txtTv.visibility = View.INVISIBLE
        viewModel.getCart(sharedPref.getAccount(activity)!!).observe(viewLifecycleOwner, Observer {
            if(it.getSize()!=0){
                txtTv.visibility=View.VISIBLE
                txtTv.text=it.getSize().toString()
            }

        })

        badgeLayout.setOnClickListener {
            onOptionsItemSelected(menu.findItem(R.id.itemCart));
        }

        val searchView = menu.findItem(R.id.itemSearch).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.i("well", " this worked 1 $query")

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.getProduct()?.observe(viewLifecycleOwner, Observer {
                   var list:ArrayList<Product> = it.filter { s -> s.mTitle!!.lowercase().contains(newText) } as ArrayList<Product>
                    var list1:ArrayList<Product> = it.filter { s -> s.mPrice!!.toBigDecimal().toPlainString().contains(newText) } as ArrayList<Product>
                    var list2:ArrayList<Product> = it.filter { s -> s.mType!!.lowercase().contains(newText) } as ArrayList<Product>

                    var list3:ArrayList<Product>
                    if(it.filter { s -> s.mTitle!!.lowercase().contains(newText) }.isNotEmpty()){

                    }
                    if(list.isNotEmpty()){
                    adapterProduct.setProduct( list)
                    }
                    if(list1.isNotEmpty()){
                    adapterProduct.setProduct( list1)
                }
                    if(list2.isNotEmpty()){
                    adapterProduct.setProduct( list2)
                }



                })
                Log.i("well", " this worked 2 $newText")
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClickRadio(type: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToListTypeProductFragment(type)
        this.findNavController().navigate(action)
    }

    override fun onCLickProduct(product: Product) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(product)
        findNavController().navigate(action)
    }

}