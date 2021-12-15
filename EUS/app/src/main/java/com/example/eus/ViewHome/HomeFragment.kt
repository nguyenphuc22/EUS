package com.example.eus.ViewHome

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
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


class HomeFragment : Fragment(), OnClickItemCategory, OnClickItemProduct{

    private lateinit var binding : FragmentHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var adapterCategory: AdapterCategory
    private lateinit var adapterProduct: AdapterProduct
    private lateinit var viewModel : EUSViewModel
    private lateinit var sharedPref:ManagerSharePref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EUSViewModel::class.java)
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
            adapterProduct.setProduct(it)

        })
        auth= FirebaseAuth.getInstance()
        sharedPref= ManagerSharePref()
        binding.recyclerListProduct.layoutManager = GridLayoutManager(context,2)
        binding.recyclerListProduct.adapter = adapterProduct
        if(auth.currentUser!=null){
        var account = Account.Builder()
            .addUsername(auth.currentUser?.email.toString())
            .addName(auth.currentUser?.displayName.toString())
            .addEmail(auth.currentUser?.email.toString())
            .addDateOfBirth(0)
            .addId("")
            .addPhone("")
            .build()
        viewModel.isExist(account).observe(viewLifecycleOwner, {
            Log.i("test123",it.toString())

            if(it==false){
                viewModel.register(account)
            }
        })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val currentUser = auth.currentUser

        when(item.itemId) {
            R.id.itemProfile -> {
                   if(sharedPref.getState(activity).equals("isLogged")){
                       this.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                   }else{
                       this.findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                   }
            }
            R.id.itemCart->{


            }
            R.id.itemSearch->{

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClickRadio(type: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToListTypeProductFragment(type)
        this.findNavController().navigate(action)
    }

    override fun onCLickProduct(product: Product) {
        val productFake = Util.fakeProduct()
        var action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(productFake)
        findNavController().navigate(action)
    }

}