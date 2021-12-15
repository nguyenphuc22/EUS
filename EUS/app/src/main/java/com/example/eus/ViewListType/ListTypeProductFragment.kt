package com.example.eus.ViewListType

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eus.R
import com.example.eus.ViewDetail.DetailFragmentArgs
import com.example.eus.ViewHome.AdapterProduct
import com.example.eus.ViewHome.Util
import com.example.eus.ViewModel.EUSViewModel
import com.example.eus.databinding.FragmentListTypeProductBinding


class ListTypeProductFragment : Fragment() {

    private lateinit var binding : FragmentListTypeProductBinding
    private lateinit var adapterProduct: AdapterProduct
    private lateinit var viewModel : EUSViewModel
    private val args: ListTypeProductFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListTypeProductBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(EUSViewModel::class.java)
        adapterProduct = AdapterProduct()
        viewModel.getListProduct(args.title).observe(viewLifecycleOwner, Observer {
            adapterProduct.setProduct(it)
        })
        binding.recyclerListProduct.adapter = adapterProduct
        binding.recyclerListProduct.layoutManager = GridLayoutManager(context,2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemProfile -> {
                this.findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}