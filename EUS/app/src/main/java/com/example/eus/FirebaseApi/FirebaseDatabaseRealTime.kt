package com.example.eus.FirebaseApi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.eus.ODT.Account
import com.example.eus.ODT.Product
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.Query

class FirebaseDatabaseRealTime : FireApiDatabase {
    private lateinit var database: DatabaseReference
    lateinit var list: ArrayList<Account>

    lateinit var accountTmp : Account

    fun getAccout1(account: Account) : MutableLiveData<Account> {
        var accountTmp : MutableLiveData<Account>
        accountTmp = MutableLiveData()
        var nulacc : Account?
        nulacc=null
        database=Firebase.database.getReference("Accounts")
        database.addChildEventListener(object :ChildEventListener{

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var tmp=snapshot.getValue<Account>()
                if(account.mUsername== tmp?.mUsername){
                    if(account.mPassword==tmp?.mPassword) {
                        accountTmp.postValue(tmp)
                        Log.i("TEST2", accountTmp.toString())
                    } else {
                        accountTmp.postValue(nulacc)
                    }
                }
                else
                    accountTmp.postValue(nulacc)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                var a=snapshot.getValue<Account>()
                Log.i("TEST1111",a.toString())
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                var a=snapshot.getValue<Account>()
                Log.i("TEST2222",a.toString())
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                var a=snapshot.getValue<Account>()
                Log.i("TEST3333",a.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("MainActivity:GetAccout",error.message)
            }

        })



        return accountTmp
    }

    fun getAccount(account : Account): Account? {
        var res = Account.Builder().build()

        database=Firebase.database.getReference("Accounts")
        database.orderByChild("musername").equalTo(account.mUsername.toString()).addChildEventListener(object :ChildEventListener{

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var tmp=snapshot.getValue<Account>()
                if (tmp != null) {
                    if(account.mPassword==tmp.mPassword) {
                        res = tmp
                        Log.i("TEST2", res.toString())
                    }
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                var a=snapshot.getValue<Account>()
                Log.i("TEST1111",a.toString())
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                var a=snapshot.getValue<Account>()
                Log.i("TEST2222",a.toString())
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                var a=snapshot.getValue<Account>()
                Log.i("TEST3333",a.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        Log.i("TEST1", res.toString())
        return res
    }
    fun pushAccount(account: Account){
        database=Firebase.database.getReference()

        database.child("Accounts").child(account.mId.toString()).setValue(account)
    }

    fun getProductType(): MutableLiveData<List<String>>?{
        var mutableLiveData : MutableLiveData<List<String>> = MutableLiveData()
        var list= ArrayList<String>()
        database=Firebase.database.getReference("Product Type")
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(type in snapshot.children){
                        val pType= type.getValue(Product::class.java)
                        list.add(pType?.mType!!)
                    }
                    mutableLiveData.value= list
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return mutableLiveData
    }
}