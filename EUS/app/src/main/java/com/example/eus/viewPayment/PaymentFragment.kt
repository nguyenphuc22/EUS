package com.example.eus.viewPayment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.util.Util
import com.example.eus.ODT.Product
import com.example.eus.R
import com.example.eus.ViewCart.AdapterCart
import com.example.eus.ViewCart.OnClickCart
import com.example.eus.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment() , OnClickCart {

    private lateinit var binding: FragmentPaymentBinding
    private lateinit var adapter : AdapterCart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AdapterCart(this)
        com.example.eus.ViewHome.Util.fakeCart().observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onClickPlus(product: Product) {
        product.mQuantity = product.mQuantity?.plus(1)
    }

    override fun onUpdatePrice(price: Double) {
        binding.includeTotalPrice.txtPrice.text = Product().Util().convertToMoney(price)
    }

    override fun onClickMinus(product: Product) {
        product.mQuantity = product.mQuantity?.minus(1)
    }

}