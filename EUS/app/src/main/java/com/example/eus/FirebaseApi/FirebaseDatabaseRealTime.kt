package com.example.eus.FirebaseApi

import com.example.eus.ODT.Account
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class FirebaseDatabaseRealTime {
    private lateinit var database: DatabaseReference

    fun getAccount(account : Account): Account? {
        var res: Account?
        res=null
        database= Firebase.database.reference.child("Accounts")
        val addChildEventListener = database.orderByChild("mID").equalTo(account.mId)
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    if (account.mPassword == snapshot.getValue<Account>()?.mPassword) {
                        res = snapshot.getValue<Account>()!!
                    }
                    println(res.toString())
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
        return res
    }
}