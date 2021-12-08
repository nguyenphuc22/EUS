package com.example.eus.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eus.FirebaseApi.FirebaseAuth
import com.example.eus.FirebaseApi.FirebaseDatabaseRealTime
import com.example.eus.FirebaseApi.Firestore
import com.example.eus.ODT.Account

class AccountRepository {

    var accounts : MutableLiveData<Account>

    var firestore : Firestore
    var firebaseAuth : FirebaseAuth
    var firebaseRealTime: FirebaseDatabaseRealTime

    init {
        firestore = Firestore()
        firebaseAuth = FirebaseAuth()
        accounts = MutableLiveData()
        firebaseRealTime= FirebaseDatabaseRealTime()
    }

    fun getAccountMutableLiveData(): MutableLiveData<Account> {
        return accounts
    }

    fun login(account : Account): Account? {

        return firebaseRealTime.getAccount(account)
    }

    fun loginGoogle(): Account?{
//        return Util.fakeAccount();
        return null;
    }

//    fun forgetAccount(account: Account): Account?{
//        return firestore.forgetAccount(account)
//    }
//
//    fun register(account: Account): Account?{
//        return firestore.register(account)
//    }
//
//    fun profile(account: Account): Account?{
//        return firestore.profile(account)
//    }
}