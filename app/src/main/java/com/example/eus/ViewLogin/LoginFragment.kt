package com.example.eus.ViewLogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.eus.R
import com.example.eus.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.itemLogin.btnRegister.setOnClickListener {
            Navigation.findNavController(binding.itemLogin.btnRegister).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.itemLogin.btnFoget.setOnClickListener {
            Navigation.findNavController(binding.itemLogin.btnFoget).navigate(R.id.action_loginFragment_to_forgetFragment)
        }

        binding.itemLogin.btnLogin.setOnClickListener {

        }
    }

}