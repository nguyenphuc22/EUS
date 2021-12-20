package com.example.eus.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eus.FirebaseApi.FireApiDatabase
import com.example.eus.FirebaseApi.FirebaseDatabaseRealTime
import com.example.eus.FirebaseApi.Firestore
import com.example.eus.ODT.Account
import com.example.eus.ODT.Cart
import com.example.eus.ODT.Product
import com.example.eus.ViewHome.Util

class AccountRepository : Repository {

    var accounts : MutableLiveData<Account>

    var firestore : Firestore

    var firebaseRealTime: FireApiDatabase

    init {
        firestore = Firestore()
        
        accounts = MutableLiveData()
        firebaseRealTime= FirebaseDatabaseRealTime()
    }

    override fun getAccountMutableLiveData(): MutableLiveData<Account> {
        return accounts
    }

    override fun login(account : Account): MutableLiveData<Account>? {

        return firebaseRealTime.getAccout1(account)
    }

    override fun loginGoogle(): Account?{
//        return Util.fakeAccount();
        return null;
    }

    override fun forgetAccount(account: Account): Account?{
        return null
    }

    override fun register(account: Account): MutableLiveData<Boolean>{

        return firebaseRealTime.pushAccount(account)
    }

    override fun profile(account: Account): Account?{
        return null
    }

    override fun getOTP(phoneNum: String): String?{
        return null
    }

    override fun changePassword(phoneNum: String, password: String){}

    fun getDataCategory(): MutableLiveData<List<String>>?{
        return firebaseRealTime.getProductType()
    }

    override fun getDataProduct(): MutableLiveData<List<Product>>{
        return firebaseRealTime.getAllProduct()
    }

    override fun isExist(account: Account): MutableLiveData<Boolean>{
        return firebaseRealTime.isExist(account)
    }

    override fun getListProduct(type: String): MutableLiveData<List<Product>> {
        return firebaseRealTime.getListProduct(type)
    }

    override fun setUser(username: String, account: Account) {
        return firebaseRealTime.setUser(username, account)
    }

    override fun addCart(product: Product, username: String) {
        firebaseRealTime.addCart(product, username)
    }

    override fun getCart(username: String): MutableLiveData<Cart> {
        return firebaseRealTime.getCart(username)
    }

    override fun search(nameProduct: String): MutableLiveData<List<Product>> {
        return firebaseRealTime.search(nameProduct)
    }
}
