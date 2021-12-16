package com.example.eus.ViewCart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eus.ODT.Product
import com.example.eus.R
import com.example.eus.ViewHome.Util
import com.example.eus.databinding.FragmentCartBinding


class CartFragment : Fragment(), OnClickCart {

    private lateinit var binding : FragmentCartBinding
    private lateinit var adapterCart: AdapterCart

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
        adapterCart = AdapterCart(this)
        Util.fakeCart().observe(viewLifecycleOwner, Observer {
            adapterCart.setData(it)
        })
        binding.recyclerCart.layoutManager = LinearLayoutManager(context)
        binding.recyclerCart.adapter = adapterCart
    }

    override fun onClickPlus(product: Product) {
        Toast.makeText(context,"Click Plus",Toast.LENGTH_SHORT).show()
        product.mQuantity = product.mQuantity?.plus(1)
    }

    override fun onClickMinus(product: Product) {
        Toast.makeText(context,"Click Minus",Toast.LENGTH_SHORT).show()
        product.mQuantity = product.mQuantity?.minus(1)
    }

}