package com.example.eus.ViewProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.eus.R
import com.example.eus.SharePref.ManagerSharePref
import com.example.eus.ViewModel.EUSViewModel
import com.example.eus.databinding.FragmentProfileBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPref :ManagerSharePref
    private lateinit var viewModel : EUSViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth=Firebase.auth
        sharedPref= ManagerSharePref()

        binding= FragmentProfileBinding.inflate(inflater,container,false)

        binding.btnLogout.setOnClickListener{
            auth.signOut()
            GoogleSignIn.getClient(this.activity, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())
                .signOut()

            sharedPref.setState(activity,"")
            sharedPref.setAccount(activity,"")
            Navigation.findNavController(binding.btnLogout).navigate(R.id.homeFragment)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EUSViewModel::class.java)
    }


}