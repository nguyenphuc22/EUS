package com.example.eus.FirebaseApi
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
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

class FirebaseAuth {
    private lateinit var context: Context
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
 fun googleLogin(){

     val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
         .requestIdToken(("886955102376-3nmiqd97rkfrgbt9kru88oob43va74qh.apps.googleusercontent.com"))
         .requestEmail()
         .build()
     googleSignInClient = GoogleSignIn.getClient(context, gso)

     val signInIntent = googleSignInClient.signInIntent
     startActivity(context,signInIntent,null)

 }
    fun firebaseAuthWithGoogle(idToken: String) {

//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                // Sign in success, update UI with the signed-in user's information
//                println("good")
//            } else {
//                // If sign in fails, display a message to the user.
//                println("bad")
  //          }
 //       }
    }
}