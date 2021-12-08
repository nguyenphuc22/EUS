package com.example.eus.ViewHome

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eus.ODT.Account
import com.example.eus.ODT.Product
import com.example.eus.R
import com.example.eus.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), ClickItemCategory{

    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapterCategory: AdapterCategory
    private lateinit var adapterProduct: AdapterProduct

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        adapterCategory = AdapterCategory(this)
        Util.fakeCategory().observe(viewLifecycleOwner, Observer {
            adapterCategory.setData(it)
        })
        binding.recyclerCategory.layoutManager = GridLayoutManager(context,5)
        binding.recyclerCategory.adapter = adapterCategory

        adapterProduct = AdapterProduct()
        Util.fakeData().observe(viewLifecycleOwner, Observer {
            adapterProduct.setProduct(it)
        })

        binding.recyclerListProduct.layoutManager = GridLayoutManager(context,2)
        binding.recyclerListProduct.adapter = adapterProduct

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemProfile -> {
                this.findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClickRadio(type: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToListTypeProductFragment(type)
        this.findNavController().navigate(action)
    }

}