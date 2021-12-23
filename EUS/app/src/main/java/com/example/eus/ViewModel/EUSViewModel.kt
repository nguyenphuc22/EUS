package com.example.eus.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.eus.Model.AccountRepository
import com.example.eus.ODT.Account
import com.example.eus.ODT.Cart
import com.example.eus.ODT.Product
import com.example.eus.ODT.ShipInfo
import com.example.eus.ViewHome.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EUSViewModel(application: Application) : AndroidViewModel(application) {

    private var repository : AccountRepository
    private var accountMutableLiveData : MutableLiveData<Account>
    private var productCacheMutableLiveData : LiveData<List<com.example.eus.Cache.Product>>

    init {
        repository = AccountRepository(application)
        accountMutableLiveData = repository.getAccountMutableLiveData()
        productCacheMutableLiveData = repository.productsCache
    }

    fun getAccount(username: String):MutableLiveData<Account>?{
        return repository.getAccount(username)
    }

    fun login(account: Account) : MutableLiveData<Account>? {
        return repository.login(account)
    }

    // Implement Login Google()
    fun loginGoogle() : Account? {
        return repository.loginGoogle()
    }

    fun forgetAccount(account: Account): Account?{
        return repository.forgetAccount(account)
    }

    fun register(account: Account): MutableLiveData<Boolean>{
        return repository.register(account)
    }

    fun profile(account: Account): Account?{
        return repository.profile(account)
    }

    fun getCategory(): MutableLiveData<List<String>>? {
        return repository.getDataCategory()
    }

    fun getProduct() : MutableLiveData<List<Product>>? {
        return repository.getDataProduct()
    }

    fun getOTP(phoneNum: String): String?{
        return repository.getOTP(phoneNum)
    }

    fun changePassword(phoneNum: String, password: String){
        return repository.changePassword(phoneNum, password)
    }
    fun isExist(account: Account): MutableLiveData<Boolean>{
        return repository.isExist(account)
    }

    fun getListProduct(type: String): MutableLiveData<List<Product>>{
        return repository.getListProduct(type)
    }

    fun setUser(username: String, account: Account){
        return repository.setUser(username, account)
    }

    fun addCart(product: Product, username: String){
        return repository.addCart(product, username)
    }

    fun getCart(username: String): MutableLiveData<Cart>{
        return repository.getCart(username)
    }

    fun search(nameProduct: String): MutableLiveData<List<Product>>{
        return repository.search(nameProduct)
    }

    fun saveCache(products : List<Product>) {
        viewModelScope.launch ( Dispatchers.IO ) {
            repository.saveCacheProducts(products)
        }
    }

    fun getCache() : LiveData<List<com.example.eus.Cache.Product>> {
        return productCacheMutableLiveData
    }

    fun deleteProductInCart(username: String, productId: String){
        return repository.deleteProductInCart(username, productId)
    }

    fun updateShipInfo(username: String, shipInfo: ShipInfo){
        return repository.updateShipInfo(username, shipInfo)
    }

    fun getShipInfoAll(username: String): MutableLiveData<List<ShipInfo>>{
        return repository.getShipInfo(username)
    }

    fun pushShipInfo(username: String, shipInfo: ShipInfo){
        return repository.pushShipInfo(username, shipInfo)
    }

    fun deleteShipInfo(username: String, shipInfoId: String){
        return repository.deleteShipInfo(username, shipInfoId)
    }
}