package com.example.eus.viewPayment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.eus.ODT.Payment
import com.example.eus.R
import com.example.eus.ViewDetail.DetailFragmentArgs
import com.example.eus.ViewHome.Util
import com.example.eus.databinding.FragmentChangePaymentBinding

class ChangePaymentFragment : Fragment() {

    private lateinit var binding : FragmentChangePaymentBinding
    private lateinit var payments : ArrayList<Payment>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangePaymentBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        binding.include.txtTitle.text = "Phương Thức Thanh Toán"
        Util.fakeTypePayment().observe(viewLifecycleOwner, Observer {
            payments = it
            for (payment in payments) {
                var radioButton = RadioButton(context)
                radioButton.id = View.generateViewId()
                radioButton.setText(payment.getType())
                binding.include.radioGroup.addView(radioButton)
            }
        })

    }


}