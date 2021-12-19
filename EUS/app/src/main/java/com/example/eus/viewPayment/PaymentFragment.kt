package com.example.eus.viewPayment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
        initView()
        adapter = AdapterCart(this)

        com.example.eus.ViewHome.Util.fakeOrder()?.observe(viewLifecycleOwner, Observer {
            adapter.setData(it.mCart!!)
            binding.includeInfo.txtTitle.text = Util.formatTitleInfo(it.mName!!,it.mPhone!!)
            binding.includeInfo.txtSubTitle.text = it.mAddress
            binding.includeTypePay.txtSubTitle.text = it.mPayment?.getType()
        })
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