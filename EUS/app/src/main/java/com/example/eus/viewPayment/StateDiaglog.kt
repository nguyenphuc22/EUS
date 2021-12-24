package com.example.eus.viewPayment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.eus.ODT.Product
import com.example.eus.R
import com.example.eus.SharePref.ManagerSharePref
import com.example.eus.SharePref.SharedPref
import com.example.eus.ViewModel.EUSViewModel
import com.example.eus.databinding.DialogStateBinding

class StateDiaglog : DialogFragment() {

    private lateinit var binding : DialogStateBinding
    private lateinit var viewModel: EUSViewModel
    private lateinit var products : List<Product>
    private lateinit var sharedPref: SharedPref
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        isCancelable = false
        viewModel = ViewModelProvider(this).get(EUSViewModel::class.java)
        sharedPref = ManagerSharePref()
        viewModel.getCart(sharedPref.getAccount(activity).toString()).observe(viewLifecycleOwner,
            Observer {
                products = it.getProducts()
            })
        binding.btn.setOnClickListener {
            for (product in products) {
                viewModel.deleteProductInCart(sharedPref.getAccount(activity).toString(),product.mID.toString())
            }
            this.findNavController().navigate(R.id.action_stateDiaglog_to_homeFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogStateBinding.inflate(inflater,container,false)

        return binding.root
    }
}