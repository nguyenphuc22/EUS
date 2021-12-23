package com.example.eus.viewPayment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eus.R
import com.example.eus.SharePref.ManagerSharePref
import com.example.eus.SharePref.SharedPref
import com.example.eus.ViewHome.Util
import com.example.eus.ViewModel.EUSViewModel
import com.example.eus.databinding.FragmentChangInfoBinding


class ChangeInfoFragment : Fragment() {

    private lateinit var binding : FragmentChangInfoBinding
    private lateinit var adapter : AdapterInfo
    private lateinit var sharedPref: SharedPref
    private lateinit var viewModel : EUSViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangInfoBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        sharedPref = ManagerSharePref()
        adapter = AdapterInfo()
        viewModel = ViewModelProvider(this).get(EUSViewModel::class.java)
        viewModel.getAccount(sharedPref.getAccount(activity).toString())?.observe(viewLifecycleOwner,
            Observer {
                it.mShipInfos?.let { it1 -> adapter.setAccounts(it1) }
            })
        binding.recyclerInfo.adapter = adapter
        binding.recyclerInfo.layoutManager = LinearLayoutManager(context)

        binding.btn.setOnClickListener {

            adapter.getInfo()?.let { it1 -> sharedPref.setShipInfo(activity,shipInfo = it1) }
            this.findNavController().navigate(R.id.action_changInfoFragment_to_paymentFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_add -> {
                this.findNavController().navigate(R.id.action_changInfoFragment_to_addInfoFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {

    }

}