package com.example.eus.ViewRegister

import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eus.R
import com.example.eus.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var binding : FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
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

        binding.include.layoutTwo.hint = resources.getString(R.string.password)
        binding.include.textTwo.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        binding.include.textTwo.transformationMethod = PasswordTransformationMethod.getInstance()

        binding.include.btn.text = resources.getText(R.string.Register)
    }

}