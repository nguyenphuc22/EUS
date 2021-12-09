package com.example.eus.ViewForget

import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.eus.R
import com.example.eus.databinding.FragmentChangePasswordBinding


class ChangePasswordFragment : Fragment() {

    private lateinit var binding : FragmentChangePasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangePasswordBinding.inflate(inflater, container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitViewState()
        binding.include.btn.setOnClickListener {
            if (!binding.include.textOne.text?.equals(binding.include.textTwo.text)!!) {
                Toast.makeText(context,"The password is not the same",Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_changePasswordFragment_to_loginFragment)
            }
        }

    }

    private fun setInitViewState() {
        binding.include.layoutOne.hint = resources.getString(R.string.password)
        binding.include.textOne.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        binding.include.textOne.transformationMethod = PasswordTransformationMethod.getInstance()

        binding.include.layoutTwo.hint = resources.getString(R.string.password_again)
        binding.include.textTwo.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        binding.include.textTwo.transformationMethod = PasswordTransformationMethod.getInstance()

        binding.include.btn.text = resources.getText(R.string.Confirm)
    }

}