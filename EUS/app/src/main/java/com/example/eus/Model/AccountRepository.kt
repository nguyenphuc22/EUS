package com.example.eus.Model

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eus.Cache.CacheDatabase
import com.example.eus.Cache.ProductDAO
import com.example.eus.FirebaseApi.FireApiDatabase
import com.example.eus.FirebaseApi.FirebaseDatabaseRealTime
import com.example.eus.FirebaseApi.Firestore
import com.example.eus.ODT.Account
import com.example.eus.ODT.Cart
import com.example.eus.ODT.Product
import com.example.eus.ODT.ShipInfo

class AccountRepository : Repository {

    var accounts : MutableLiveData<Account>
    var productsCache : LiveData<List<com.example.eus.Cache.Product>>

    var firestore : Firestore

    var firebaseRealTime: FireApiDatabase

    private var productDAO: ProductDAO

    constructor(application: Application) {
        productDAO = CacheDatabase.getInstance(application).productDao()
        productsCache = productDAO.getAll()
    }

    init {
        firestore = Firestore()
        accounts = MutableLiveData()
        firebaseRealTime= FirebaseDatabaseRealTime()
    }

    override fun getAccount(username: String): MutableLiveData<Account> {
        return firebaseRealTime.getAccout(username)
    }

    override fun getAccountMutableLiveData(): MutableLiveData<Account> {
        return accounts
    }

    override fun login(account : Account): MutableLiveData<Account>? {
//        return null
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

    override suspend fun saveCacheProducts(products: List<Product>) {
        productDAO.deleteAll()
        for (product in products) {
            val productCache = com.example.eus.Cache.Product.Builder()
                .addID(product.mID!!)
                .addImage(product.mImage!!)
                .addTitle(product.mTitle!!)
                .addPrice(product.mPrice!!)
                .addQuantity(product.mQuantity!!)
                .addName(product.mName!!)
                .addType(product.mType!!)
                .build()
            productDAO.insert(product = productCache)
            Log.i("Insert",productCache.toString())
        }
    }

    override suspend fun getCacheProducts(): LiveData<List<com.example.eus.Cache.Product>> {
        return productDAO.getAll()
    }

    override fun deleteProductInCart(username: String, productId: String) {
        return firebaseRealTime.deleteProductInCart(username, productId)
    }

    override fun updateShipInfo(username: String, shipInfo: ShipInfo) {
        return firebaseRealTime.updateShipInfo(username, shipInfo)
    }

    override fun deleteShipInfo(username: String, shipInfoId: String) {
        return firebaseRealTime.removeShipInfo(username, shipInfoId)
    }

    override fun pushShipInfo(username: String, shipInfo: ShipInfo) {
        return firebaseRealTime.pushShipInfo(username, shipInfo)
    }

    override fun getShipInfo(username: String, shipInfoId: String): MutableLiveData<ShipInfo> {
        return firebaseRealTime.getShipInfo(username, shipInfoId)
    }
}
