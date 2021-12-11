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

class FirebaseDatabaseRealTime {
    private lateinit var database: DatabaseReference
    lateinit var list: ArrayList<Account>

    lateinit var accountTmp : Account

    fun getAccout1(account: Account) : MutableLiveData<Account> {
        var accountTmp : MutableLiveData<Account>
        accountTmp = MutableLiveData()
        var nulacc=Account.Builder().build()
        accountTmp.postValue(nulacc)
        database=Firebase.database.getReference("Accounts")
        database.orderByChild("musername").equalTo(account.mUsername.toString()).addChildEventListener(object :ChildEventListener{

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var tmp=snapshot.getValue<Account>()
                if(account.mPassword==tmp?.mPassword) {
                    accountTmp.postValue(tmp)
                    Log.i("TEST2", accountTmp.toString())
                } else {
                    accountTmp.postValue(nulacc)
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

    fun getproductType(): ArrayList<String>?{
        var list= ArrayList<String>()
        database=Firebase.database.getReference("Products")
        database.orderByChild("mtype").addChildEventListener(object:ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var tmp=snapshot.getValue<Product>()
                if(tmp!=null){
                    list.add(tmp.mType.toString())
                    Log.i("Testasd",list.toString())
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return list
    }
}