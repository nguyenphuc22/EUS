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
        binding.include.btn.setOnClickListener {
            if (!binding.include.text.text?.isBlank()!!) {
                findNavController().navigate(R.id.action_forgetFragment_to_verificationFragment)
            } else {
                Toast.makeText(context,"Number is not blank",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setInitViewState() {
        binding.include.layout.hint = resources.getString(R.string.phone)
        binding.include.text.inputType = InputType.TYPE_CLASS_PHONE

        binding.include.btn.text = resources.getString(R.string.Send)
    }

}