package com.example.eus.FirebaseApi

import androidx.lifecycle.MutableLiveData
import com.example.eus.ODT.Account

interface FireApiDatabase {
    fun getAccout1(account: Account) : MutableLiveData<Account>
    fun pushAccount(account: Account): MutableLiveData<Boolean>
    fun getProductType(): MutableLiveData<List<String>>?
    fun isExist(account: Account):MutableLiveData<Boolean>
}