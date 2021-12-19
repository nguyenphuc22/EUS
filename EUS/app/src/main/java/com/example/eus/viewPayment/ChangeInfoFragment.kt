package com.example.eus.viewPayment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eus.R
import com.example.eus.ViewHome.Util
import com.example.eus.databinding.FragmentChangInfoBinding


class ChangeInfoFragment : Fragment() {

    private lateinit var binding : FragmentChangInfoBinding
    private lateinit var adapter : AdapterInfo
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
        adapter = AdapterInfo()
        Util.fakeAccounts().observe(viewLifecycleOwner, Observer {
            adapter.setAccounts(it)
        })
        binding.recyclerInfo.adapter = adapter
        binding.recyclerInfo.layoutManager = LinearLayoutManager(context)
    }

    private fun initView() {

    }

}