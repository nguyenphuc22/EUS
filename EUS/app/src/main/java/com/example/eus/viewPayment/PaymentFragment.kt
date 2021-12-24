package com.example.eus.viewPayment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eus.ODT.COD
import com.example.eus.ODT.Cart
import com.example.eus.ODT.Order
import com.example.eus.ODT.Product
import com.example.eus.R
import com.example.eus.SharePref.ManagerSharePref
import com.example.eus.SharePref.SharedPref
import com.example.eus.ViewCart.AdapterCart
import com.example.eus.ViewCart.OnClickCart
import com.example.eus.ViewModel.EUSViewModel
import com.example.eus.databinding.FragmentPaymentBinding
import java.util.*
import kotlin.collections.ArrayList


class PaymentFragment : Fragment() , OnClickCart {

    private lateinit var binding: FragmentPaymentBinding
    private lateinit var adapter : AdapterCart
    private lateinit var order: Order
    private lateinit var viewModel : EUSViewModel
    private lateinit var sharedPref: SharedPref
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
        adapter = AdapterCart(this)
        viewModel = ViewModelProvider(this).get(EUSViewModel::class.java)

        val products = ArrayList<Product>()
        if (args.ListProduct != null) {
            for (product in args.ListProduct!!) {
                products.add(product)
            }
        } else {
            val productCache =  viewModel.getCache()
            productCache.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                for (product in it) {
                    adapter.addProduct(product = Product.Builder()
                        .addID(product.mID!!)
                        .addQuantity(product.mQuantity!!)
                        .addImage(product.mImage!!)
                        .addName(product.mName!!)
                        .addPrice(product.mPrice!!)
                        .addTitle(product.mTitle!!)
                        .addType(product.mType!!)
                        .build())
                }

            })
        }
        sharedPref = ManagerSharePref()
        val shipInfo = sharedPref.getShipInfo(activity)
        order = Order.Builder()
            .addCart(Cart(products))
            .addPayment(COD())
            .addShipInfo(shipInfo)
            .build()
        adapter.setData(order.mCart!!)

        if (order.shipInfo?.name != null && order.shipInfo?.phone != null)
         binding.includeInfo.txtTitle.text = Util.formatTitleInfo(order.shipInfo?.name!!,order.shipInfo?.phone!!)
        if (order.shipInfo?.address != null)
         binding.includeInfo.txtSubTitle.text = order.shipInfo?.address
        if (order.mPayment != null) {
            binding.includeTypePay.txtSubTitle.text = order.mPayment?.getType()
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.includeInfo.root.setOnClickListener {
            viewModel.saveCache(products)
            this.findNavController().navigate(R.id.action_paymentFragment_to_changInfoFragment)
        }

        binding.includeTypePay.root.setOnClickListener {
            viewModel.saveCache(products)
            this.findNavController().navigate(R.id.action_paymentFragment_to_changePaymentFragment)
        }
        binding.includeTotalPrice.btnBuy.setOnClickListener {
            this.findNavController().navigate(R.id.action_paymentFragment_to_stateDiaglog)
        }
    }

    fun initView() {
        binding.includeTypePay.txtTitle.text = getString(R.string.payment)
    }

    override fun onClickPlus(product: Product) {
        product.mQuantity = product.mQuantity?.plus(1)
        viewModel.addCart(product,sharedPref.getAccount(activity).toString())
    }

    override fun onUpdatePrice(price: Double) {
        binding.includeTotalPrice.txtPrice.text = Product().Util().convertToMoney(price)
    }

    override fun onClickMinus(product: Product) {
        product.mQuantity = product.mQuantity?.minus(1)
        if(product.mQuantity!! > 0) {
            viewModel.addCart(product,sharedPref.getAccount(activity).toString())
        } else {
            viewModel.deleteProductInCart(
                productId = product.mID.toString(),
                username = sharedPref.getAccount(activity).toString())
        }
    }

    override fun onDelete(product: Product) {
        viewModel.deleteProductInCart(
            productId = product.mID.toString(),
            username = sharedPref.getAccount(activity).toString())
    }

}