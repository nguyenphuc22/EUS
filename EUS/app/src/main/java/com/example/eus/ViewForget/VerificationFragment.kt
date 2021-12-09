package com.example.eus.ViewForget

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.eus.R
import com.example.eus.databinding.FragmentVerificationBinding

class VerificationFragment : Fragment() {

    private lateinit var binding: FragmentVerificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVerificationBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitViewState()
        binding.include.btn.setOnClickListener {
            if (!binding.include.text.text?.isBlank()!!) {
                findNavController().navigate(R.id.action_verificationFragment_to_changePasswordFragment)
            } else {
                Toast.makeText(context,"OTP is not blank",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setInitViewState() {
        binding.include.layout.hint = resources.getString(R.string.OTP)
        binding.include.text.inputType = InputType.TYPE_CLASS_NUMBER


        binding.include.btn.text = resources.getString(R.string.Confirm)
    }

}