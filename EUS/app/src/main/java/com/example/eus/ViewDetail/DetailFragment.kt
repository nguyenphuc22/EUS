package com.example.eus.ViewDetail

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
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

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemCart->{
                this.findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        var itemSearch : MenuItem = menu.findItem(R.id.itemSearch)
        itemSearch.isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }


}