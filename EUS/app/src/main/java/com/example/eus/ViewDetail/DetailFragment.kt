package com.example.eus.ViewDetail

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.eus.ODT.Product
import com.example.eus.R
import com.example.eus.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var adapterDetail: AdapterDetail
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
        Glide.with(this)
            .load(args.productDetail?.mImage)
            .fitCenter()
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imgProduct)

        binding.txtTitle.text = args.productDetail?.mTitle
        binding.txtPrice.text =Product().Util().convertToMoney(args.productDetail?.mPrice)
        binding.txtDescription.text = args.productDetail?.mDescription
        adapterDetail = AdapterDetail(args.productDetail?.mDetails!!)
        binding.include.recyclerDetail.layoutManager = LinearLayoutManager(context)
        binding.include.recyclerDetail.adapter = adapterDetail
    }

}