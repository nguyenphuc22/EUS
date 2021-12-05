package com.example.eus.ViewLogin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.eus.ODT.Account
import com.example.eus.R
import com.example.eus.ViewModel.EUSViewModel
import com.example.eus.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var viewModel: EUSViewModel

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

        viewModel = ViewModelProvider(this).get(EUSViewModel::class.java)


        binding.itemLogin.btnRegister.setOnClickListener {
            Navigation.findNavController(binding.itemLogin.btnRegister).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.itemLogin.btnFoget.setOnClickListener {
            Navigation.findNavController(binding.itemLogin.btnFoget).navigate(R.id.action_loginFragment_to_forgetFragment)
        }

        binding.itemLogin.btnLogin.setOnClickListener {
            val account = Account.Builder()
                .addName(binding.itemLogin.textOne.text.toString())
                .addPassword(binding.itemLogin.textTwo.text.toString())
                .build()

            if(viewModel.login(account) != null) {

                Toast.makeText(context,"Sucess",Toast.LENGTH_SHORT).show()

            } else {

                Toast.makeText(context,"Fail",Toast.LENGTH_SHORT).show()

            }

        }
    }

}