package com.example.eus.viewPayment

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.eus.ODT.ShipInfo
import com.example.eus.R
import com.example.eus.SharePref.ManagerSharePref
import com.example.eus.SharePref.SharedPref
import com.example.eus.ViewDetail.DetailFragmentArgs
import com.example.eus.ViewModel.EUSViewModel
import com.example.eus.databinding.FragmentAddInfoBinding
import kotlin.random.Random

class AddInfoFragment : Fragment() {
    private val args: AddInfoFragmentArgs by navArgs()
    private lateinit var binding: FragmentAddInfoBinding
    private lateinit var viewModel : EUSViewModel
    private lateinit var sharedPref: SharedPref
    private lateinit var shipInfos: List<ShipInfo>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddInfoBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EUSViewModel::class.java)


        sharedPref = ManagerSharePref()
        if (args.shipInfo != null) {
            binding.edtAddress.text = Editable.Factory.getInstance().newEditable(args.shipInfo!!.address)
            binding.edtPhone.text = Editable.Factory.getInstance().newEditable(args.shipInfo!!.phone)
            binding.edtName.text = Editable.Factory.getInstance().newEditable(args.shipInfo!!.name)
        }

        viewModel.getShipInfoAll(sharedPref.getAccount(activity).toString()).observe(viewLifecycleOwner,
            Observer {
                shipInfos = it
            }
        )


        binding.button.setOnClickListener {
            var i = 0
            for (shipinfo in shipInfos) {
                if (shipinfo.id?.toInt()!! >= i) {
                    i = shipinfo.id?.toInt()!!
                    i++
                }
            }
            val shipInfo = ShipInfo.Builder()
                .addId(i.toString())
                .addPhone(binding.edtPhone.text.toString())
                .addName(binding.edtName.text.toString())
                .addAddress(binding.edtAddress.text.toString())
                .build()
            if (args.shipInfo != null) {
                viewModel.updateShipInfo(sharedPref.getAccount(activity).toString(),shipInfo)
            } else {
                viewModel.pushShipInfo(sharedPref.getAccount(activity).toString(),shipInfo)
            }
            this.findNavController().navigate(R.id.action_addInfoFragment_to_changInfoFragment)
        }
    }

}