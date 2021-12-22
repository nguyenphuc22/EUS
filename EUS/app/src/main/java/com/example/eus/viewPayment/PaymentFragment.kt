package com.example.eus.viewPayment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eus.ODT.COD
import com.example.eus.ODT.Cart
import com.example.eus.ODT.Order
import com.example.eus.ODT.Product
import com.example.eus.R
import com.example.eus.ViewCart.AdapterCart
import com.example.eus.ViewCart.OnClickCart
import com.example.eus.databinding.FragmentPaymentBinding
import java.util.*
import kotlin.collections.ArrayList


class PaymentFragment : Fragment() , OnClickCart {

    private lateinit var binding: FragmentPaymentBinding
    private lateinit var adapter : AdapterCart
    private lateinit var order: Order
    private val args: PaymentFragmentArgs by navArgs()
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
        initView()
        val products = ArrayList<Product>()
        for (product in args.ListProduct) {
            products.add(product)
        }
        adapter = AdapterCart(this)
        order = Order.Builder()
            .addCart(Cart(products))
            .addName("Thanh Bu Liem")
            .addPhone("089666")
            .addAddress("Hoa Thanh Ti")
            .addPayment(COD())
            .build()
        adapter.setData(order.mCart!!)
        if (order.mName != null && order.mPhone != null)
         binding.includeInfo.txtTitle.text = Util.formatTitleInfo(order.mName!!,order.mPhone!!)
        if (order.mAddress != null)
         binding.includeInfo.txtSubTitle.text = order.mAddress
        if (order.mPayment != null) {
            binding.includeTypePay.txtSubTitle.text = order.mPayment?.getType()
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.includeInfo.root.setOnClickListener {
            this.findNavController().navigate(R.id.action_paymentFragment_to_changInfoFragment)
        }

        binding.includeTypePay.root.setOnClickListener {
            this.findNavController().navigate(R.id.action_paymentFragment_to_changePaymentFragment)
        }
    }

    fun initView() {
        binding.includeTypePay.txtTitle.text = getString(R.string.payment)
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