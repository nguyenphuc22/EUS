package com.example.eus.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eus.FirebaseApi.FirebaseAuth
import com.example.eus.FirebaseApi.Firestore
import com.example.eus.ODT.Account

class AccountRepository {

    var accounts : MutableLiveData<Account>

    var firestore : Firestore
    var firebaseAuth : FirebaseAuth

    init {
        firestore = Firestore()
        firebaseAuth = FirebaseAuth()
        accounts = MutableLiveData()
    }

    fun getAccountMutableLiveData(): MutableLiveData<Account> {
        return accounts
    }

    fun login(account : Account): Account? {
        return null
    }
}