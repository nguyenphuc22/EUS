package com.example.eus.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eus.FirebaseApi.FirebaseDatabaseRealTime
import com.example.eus.FirebaseApi.Firestore
import com.example.eus.ODT.Account
import com.example.eus.ODT.Product
import com.example.eus.ViewHome.Util

class AccountRepository {

    var accounts : MutableLiveData<Account>

    var firestore : Firestore

    var firebaseRealTime: FirebaseDatabaseRealTime

    init {
        firestore = Firestore()
        
        accounts = MutableLiveData()
        firebaseRealTime= FirebaseDatabaseRealTime()
    }

    fun getAccountMutableLiveData(): MutableLiveData<Account> {
        return accounts
    }

    fun login(account : Account): MutableLiveData<Account>? {

        return firebaseRealTime.getAccout1(account)
    }

    fun loginGoogle(): Account?{
//        return Util.fakeAccount();
        return null;
    }

    fun forgetAccount(account: Account): Account?{
        return null
    }

    fun register(account: Account): Account?{
        return null
    }

    fun profile(account: Account): Account?{
        return null
    }

    fun getOTP(phoneNum: String): String?{
        return null
    }

    fun changePassword(phoneNum: String, password: String){}

    fun getDataCategory(): MutableLiveData<List<String>>?{
        return firebaseRealTime.getProductType()
//        return Util.fakeCategory()
    }

    fun getDataProduct(): MutableLiveData<List<Product>>{
//        var mutableLiveData : MutableLiveData<List<Product>> = MutableLiveData()
//        var list = firestore.getDataProduct()
//        mutableLiveData.value= list
//        return mutableLiveData
        return Util.fakeData()
    }
}
