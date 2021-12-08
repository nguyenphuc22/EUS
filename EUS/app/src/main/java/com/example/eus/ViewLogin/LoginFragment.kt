package com.example.eus.ViewLogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.eus.MainActivity
import com.example.eus.ODT.Account
import com.example.eus.R
import com.example.eus.ViewModel.EUSViewModel
import com.example.eus.FirebaseApi.FirebaseAuth
import com.example.eus.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.android.gms.auth.api.signin.GoogleSignInClient


class LoginFragment : Fragment() {
    private lateinit var auth : FirebaseAuth
    private lateinit var binding : FragmentLoginBinding
    private lateinit var viewModel: EUSViewModel

    private lateinit var googleSignInClient: GoogleSignInClient
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

        binding.itemLogin.btnLoginGoogle.setOnClickListener {

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(("886955102376-3nmiqd97rkfrgbt9kru88oob43va74qh.apps.googleusercontent.com"))
                .requestEmail()
                .build()
            googleSignInClient = GoogleSignIn.getClient(this.activity, gso)

            val signInIntent = googleSignInClient.signInIntent
            startActivity(signInIntent, null)


        }

        binding.itemLogin.btnLogin.setOnClickListener {
            val account = Account.Builder()
                .addUsername(binding.itemLogin.textOne.text.toString())
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