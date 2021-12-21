package com.example.eus.ViewDetail

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.eus.ODT.Product
import com.example.eus.R
import com.example.eus.SharePref.ManagerSharePref
import com.example.eus.ViewHome.Util
import com.example.eus.ViewModel.EUSViewModel
import com.example.eus.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var adapterDetail: AdapterDetail
    private lateinit var txtTv :TextView
    private  var size =0
    private lateinit var viewModel : EUSViewModel
    private lateinit var sharedPref: ManagerSharePref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EUSViewModel::class.java)
        sharedPref= ManagerSharePref()

        Glide.with(this)
            .load(args.productDetail?.mImage)
            .fitCenter()
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imgProduct)

        binding.txtTitle.text = args.productDetail?.mTitle
        binding.txtPrice.text =Product().Util().convertToMoney(args.productDetail?.mPrice)
        binding.txtDescription.text = args.productDetail?.mDescription
        if (args.productDetail?.mDetails != null)
        {
            adapterDetail = args.productDetail?.mDetails?.let { AdapterDetail(it) }!!
            binding.include.recyclerDetail.adapter = adapterDetail
        }
        binding.include.recyclerDetail.layoutManager = LinearLayoutManager(context)
        binding.txtAddCart.setOnClickListener{
            txtTv.visibility=View.VISIBLE
            viewModel.addCart(args.productDetail!!,sharedPref.getAccount(activity)!!)
            viewModel.getCart(sharedPref.getAccount(activity)!!).observe(viewLifecycleOwner, Observer {
                if(it.getSize()!=0){
                    txtTv.visibility=View.VISIBLE
                    txtTv.text=it.getSize().toString()
                }
            })
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemCart->{
                this.findNavController().navigate(R.id.action_detailFragment_to_cartFragment)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        var itemSearch : MenuItem = menu.findItem(R.id.itemSearch)
        itemSearch.isVisible = false
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

        super.onCreateOptionsMenu(menu, inflater)
    }


}