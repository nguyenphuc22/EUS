package com.example.eus.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.eus.Model.AccountRepository
import com.example.eus.ODT.Account
import com.example.eus.ODT.Product
import com.example.eus.ViewHome.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EUSViewModel(application: Application) : AndroidViewModel(application) {

    private var repository : AccountRepository
    private var accountMutableLiveData : MutableLiveData<Account>

    init {
        repository = AccountRepository()
        accountMutableLiveData = repository.getAccountMutableLiveData()
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

    fun register(account: Account): Account?{
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
}