package com.example.eus.ViewProfile

import android.accounts.Account
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.util.Util
import com.example.eus.R
import com.example.eus.SharePref.ManagerSharePref
import com.example.eus.SharePref.SharedPref
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
        sharedPref = ManagerSharePref()
        viewModel.getAccount(sharedPref.getAccount(activity).toString())?.observe(viewLifecycleOwner,
            Observer {
                if (it.mName != null) {
                    binding.editName.text = Editable.Factory.getInstance().newEditable(it.mName)
                }
                if (it.mEmail != null) {
                    binding.editMail.text = Editable.Factory.getInstance().newEditable(it.mEmail)
                }
                if (it.mPassword != null) {
                    binding.editPassword.text = Editable.Factory.getInstance().newEditable(it.mPassword)
                }
                if (it.mPhone != null) {
                    binding.txtPhoneInfor.text = Editable.Factory.getInstance().newEditable(it.mPhone)
                }
            })

        binding.btnSave.setOnClickListener {
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
            val account = com.example.eus.ODT.Account.Builder()
                .addName(binding.editName.text.toString())
                .addEmail(binding.editMail.text.toString())
                .addPhone(binding.txtPhoneInfor.text.toString())
                .addUsername(sharedPref.getAccount(activity).toString())
                .addPassword(binding.editPassword.text.toString())
                .build()
            viewModel.setUser(sharedPref.getAccount(activity).toString(),account = account)
        }

        binding.btnLogout.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }
    }


}