package com.example.eus.FirebaseApi

import androidx.lifecycle.MutableLiveData
import com.example.eus.ODT.Account
import com.example.eus.ODT.Cart
import com.example.eus.ODT.Product
import com.example.eus.ODT.ShipInfo

interface FireApiDatabase {
    fun getAccout1(account: Account): MutableLiveData<Account>
    fun getAccout(username: String) : MutableLiveData<Account>
    fun pushAccount(account: Account): MutableLiveData<Boolean>
    fun getProductType(): MutableLiveData<List<String>>
    fun isExist(account: Account):MutableLiveData<Boolean>
    fun getAllProduct(): MutableLiveData<List<Product>>
    fun getListProduct(type: String): MutableLiveData<List<Product>>
    fun setUser(username: String, account: Account)
    fun search(name: String): MutableLiveData<List<Product>>
    fun addCart(product: Product, username: String)
    fun getCart(username: String): MutableLiveData<Cart>
    fun deleteProductInCart(username: String, productId: String)
    fun pushShipInfo(username: String, shipInfo: ShipInfo)
    fun updateShipInfo(username: String, shipInfo: ShipInfo)
    fun getShipInfo(username: String, idShipInfo: String): MutableLiveData<ShipInfo>
    fun removeShipInfo(username: String, idShipInfo: String)
}