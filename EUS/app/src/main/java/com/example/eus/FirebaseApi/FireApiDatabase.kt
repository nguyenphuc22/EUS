package com.example.eus.FirebaseApi

import androidx.lifecycle.MutableLiveData
import com.example.eus.ODT.Account
import com.example.eus.ODT.Cart
import com.example.eus.ODT.Product

interface FireApiDatabase {
    fun getAccout1(account: Account) : MutableLiveData<Account>
    fun pushAccount(account: Account): MutableLiveData<Boolean>
    fun getProductType(): MutableLiveData<List<String>>
    fun isExist(account: Account):MutableLiveData<Boolean>
    fun getAllProduct(): MutableLiveData<List<Product>>
    fun getListProduct(type: String): MutableLiveData<List<Product>>
    fun setUser(username: String, account: Account)
    fun search(name: String): MutableLiveData<List<Product>>
    fun addCart(product: Product, username: String)
    fun getCart(username: String): MutableLiveData<Cart>
}