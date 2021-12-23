package com.example.eus.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eus.ODT.Account
import com.example.eus.ODT.Cart
import com.example.eus.ODT.Product
import com.example.eus.ODT.ShipInfo

interface Repository {
    fun getAccount(username: String): MutableLiveData<Account>
    fun getAccountMutableLiveData(): MutableLiveData<Account>
    fun login(account : Account): MutableLiveData<Account>?
    fun loginGoogle(): Account?
    fun forgetAccount(account: Account): Account?
    fun register(account: Account): MutableLiveData<Boolean>
    fun profile(account: Account): Account?
    fun getOTP(phoneNum: String): String?
    fun changePassword(phoneNum: String, password: String)
    fun getDataProduct(): MutableLiveData<List<Product>>
    fun isExist(account: Account): MutableLiveData<Boolean>
    fun getListProduct(type: String): MutableLiveData<List<Product>>
    fun setUser(username: String, account: Account)
    fun addCart(product: Product, username: String)
    fun getCart(username: String): MutableLiveData<Cart>
    fun search(nameProduct: String): MutableLiveData<List<Product>>
    suspend fun saveCacheProducts(products : List<Product>)
    suspend fun getCacheProducts() : LiveData<List<com.example.eus.Cache.Product>>
    fun deleteProductInCart(username: String, productId: String)
    fun updateShipInfo(username: String, shipInfo: ShipInfo)
    fun deleteShipInfo(username: String, shipInfoId: String)
    fun pushShipInfo(username: String, shipInfo: ShipInfo)
    fun getShipInfo(username: String, shipInfoId: String):MutableLiveData<ShipInfo>

}