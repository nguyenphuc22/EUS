package com.example.eus.ViewLogin

import android.content.Context
import android.content.Intent
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.example.eus.FirebaseApi.FirebaseDatabaseRealTime
import com.example.eus.Model.AccountRepository
import com.example.eus.SharePref.ManagerSharePref
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    private lateinit var firebaseDatabaseRealTime: FirebaseDatabaseRealTime
    private lateinit var sharePref: ManagerSharePref
    private lateinit var binding : FragmentLoginBinding
    private lateinit var viewModel: EUSViewModel
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var accountRepository: AccountRepository


    var resultLauncher = registerForActivityResult(
        StartActivityForResult()
    ){ result ->

        val data: Intent? = result.data
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)

        val exception = task.exception
        if (task.isSuccessful) {
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
                println("log ok")
                firebaseAuthWithGoogle(account.idToken!!)

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("SignInActivity", "Google sign in failed", e)
                println("log failled1")
            }
        } else {
            println("log failled2")
            Log.w("SignInActivity2", exception.toString())
        }

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(("285866575552-d1i16nr90kbr5aqc84hfj6rb5eu1l6no.apps.googleusercontent.com"))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this.activity, gso)
        auth= Firebase.auth
        sharePref= ManagerSharePref()
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


         val signInIntent = googleSignInClient.signInIntent
            resultLauncher.launch((signInIntent))


            sharePref.setState(activity,"isLogged")

        }

        binding.itemLogin.btnLogin.setOnClickListener {

            val account = Account.Builder()
                .addUsername(binding.itemLogin.textOne.text.toString())
                .addPassword(binding.itemLogin.textTwo.text.toString())
                .build()

            viewModel.login(account)?.observe(viewLifecycleOwner, Observer {
                if (it == null) {

                    Toast.makeText(context,"Fail" + it.toString(),Toast.LENGTH_SHORT).show()

                } else {

                    Toast.makeText(context,"Success" + it.toString(),Toast.LENGTH_SHORT).show()

                }
            })
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SignInActivity", "signInWithCredential:success")

                    var account =Account.Builder()
                        .addUsername(auth.currentUser?.email.toString())
                        .addName(auth.currentUser?.displayName.toString())
                        .addEmail(auth.currentUser?.email.toString())
                        .addDateOfBirth(0)
                        .addId("")
                        .addPhone("")
                        .build()


                    viewModel.isExist(account).observe(viewLifecycleOwner, Observer {
                        Log.i("test123",it.toString())

                        if(it==false){
                                viewModel.register(account)
                        }
                    })


                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("SignInActivity", "signInWithCredential:failure")
                }
            }
    }


}