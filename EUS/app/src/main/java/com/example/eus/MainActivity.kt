package com.example.eus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.eus.FirebaseApi.FirebaseDatabaseRealTime
import com.example.eus.ODT.Account
import com.example.eus.ODT.ShipInfo
import com.example.eus.databinding.ActivityMainBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment
        navController = navHostFragment.findNavController()

        val firebaseDatabase = FirebaseDatabaseRealTime()
        var ship =ShipInfo.Builder()
            .addAddress("add")
            .addId("1")
            .addName("A")
            .addPhone(phone = "090").build()
        var ship1 =ShipInfo.Builder()
            .addAddress("add")
            .addId("2")
            .addName("B")
            .addPhone(phone = "090").build()
        var ship2 =ShipInfo.Builder()
            .addAddress("add")
            .addId("2")
            .addName("C")
            .addPhone(phone = "090").build()

        var b:ArrayList<ShipInfo>
        b= ArrayList<ShipInfo>()
        b.add(ship)
        b.add(ship1)
        b.add(ship2)
        var account = Account.Builder()
            .addId("7")
            .addDateOfBirth(15)
            .addEmail("mail")
            .addImage("url")
            .addPhone("phone")
            .addName("ngphuc")
            .addUsername("8416")
            .addPassword("123")
            .addAddress("mdf")
            .addShipInfos(b)
            .build()

        var a=firebaseDatabase.deleteProductInCart("8416456","bbb")


        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}