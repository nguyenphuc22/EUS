package com.example.eus.ViewCart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eus.ODT.Product
import com.example.eus.R
import com.example.eus.SharePref.ManagerSharePref
import com.example.eus.ViewHome.Util
import com.example.eus.ViewModel.EUSViewModel
import com.example.eus.databinding.FragmentCartBinding


class CartFragment : Fragment(), OnClickCart {

    private lateinit var binding : FragmentCartBinding
    private lateinit var adapterCart: AdapterCart
    private lateinit var viewModel : EUSViewModel
    private lateinit var sharedPref: ManagerSharePref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EUSViewModel::class.java)
        sharedPref= ManagerSharePref()
        initView()
        adapterCart = AdapterCart(this)
//        Util.fakeCart().observe(viewLifecycleOwner, Observer {
//            Log.i("Cart",it.toString())
//            adapterCart.setData(it)
//        })
        viewModel.getCart(sharedPref.getAccount(activity)!!).observe(viewLifecycleOwner, Observer {
            adapterCart.setData(it)
        })

        binding.recyclerCart.layoutManager = LinearLayoutManager(context)
        binding.recyclerCart.adapter = adapterCart

        binding.include.btnBuy.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_paymentFragment)
        }
    }

    fun initView() {
        binding.include.txtPrice.text = getString(R.string.titlePrice)
        binding.include.btnBuy.text = getString(R.string.payment)
    }

    override fun onClickPlus(product: Product) {
        product.mQuantity = product.mQuantity?.plus(1)
    }

    override fun onUpdatePrice(price: Double) {
        binding.include.txtPrice.text = Product().Util().convertToMoney(price)
    }

    override fun onClickMinus(product: Product) {
        product.mQuantity = product.mQuantity?.minus(1)
    }

}