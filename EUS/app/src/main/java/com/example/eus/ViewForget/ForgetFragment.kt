package com.example.eus.ViewForget

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eus.R
import com.example.eus.databinding.FragmentForgetBinding


class ForgetFragment : Fragment() {

    private lateinit var binding: FragmentForgetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgetBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitViewState()
    }

    private fun setInitViewState() {
        binding.include.layoutOne.hint = resources.getString(R.string.phone)
        binding.include.textOne.inputType = InputType.TYPE_CLASS_PHONE

        binding.include.layoutTwo.hint = resources.getString(R.string.OTP)
        binding.include.textTwo.inputType = InputType.TYPE_CLASS_NUMBER

        binding.include.btn.text = resources.getString(R.string.Confirm)
    }

}