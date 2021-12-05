package com.example.eus.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.eus.Model.AccountRepository
import com.example.eus.ODT.Account
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EUSViewModel(application: Application) : AndroidViewModel(application) {

    private var repository : AccountRepository
    private var accountMutableLiveData : MutableLiveData<Account>

    init {
        repository = AccountRepository()
        accountMutableLiveData = repository.getAccountMutableLiveData()
    }

    fun login(account: Account) : Account? {
       return repository.login(account)
    }

}