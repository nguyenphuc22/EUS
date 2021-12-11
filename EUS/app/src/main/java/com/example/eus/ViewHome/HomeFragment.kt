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
import com.example.eus.R
import com.example.eus.ViewModel.EUSViewModel
import com.example.eus.databinding.FragmentHomeBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment(), ClickItemCategory{

    private lateinit var binding : FragmentHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var adapterCategory: AdapterCategory
    private lateinit var adapterProduct: AdapterProduct
    private lateinit var viewModel : EUSViewModel
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
        adapterCategory = AdapterCategory(this)
        viewModel.getCategory()?.observe(viewLifecycleOwner, Observer {
            adapterCategory.setData(it)
        })
        binding.recyclerCategory.layoutManager = GridLayoutManager(context,5)
        binding.recyclerCategory.adapter = adapterCategory

        adapterProduct = AdapterProduct()
        viewModel.getProduct()?.observe(viewLifecycleOwner, Observer {
            adapterProduct.setProduct(it)

        })
        auth=Firebase.auth
        binding.recyclerListProduct.layoutManager = GridLayoutManager(context,2)
        binding.recyclerListProduct.adapter = adapterProduct

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val currentUser = auth.currentUser
        when(item.itemId) {
            R.id.itemProfile -> {
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

                if (sharedPref != null) {
                   // println(sharedPref.getString("isLogin","false"))
                   if(sharedPref.getString("isLogin","false").equals("isLogged")){
                       this.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                   }else{
                       this.findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                   }
                }

            }
            R.id.itemCart->{


            }
            R.id.itemSearch->{
                auth.signOut()
                GoogleSignIn.getClient(this.activity, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())
                    .signOut()
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                if (sharedPref != null) {
                    with (sharedPref.edit()) {
                        putString("isLogin","")
                        apply()
                    }
                }
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

}