package com.example.eus.FirebaseApi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.eus.ODT.Account
import com.example.eus.ODT.Cart
import com.example.eus.ODT.Product
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class FirebaseDatabaseRealTime : FireApiDatabase {
    private lateinit var database: DatabaseReference
    lateinit var list: ArrayList<Account>



    override fun getAccout1(account: Account) : MutableLiveData<Account> {
        var isTrue : Boolean = true
        var accountTmp : MutableLiveData<Account>
        accountTmp = MutableLiveData()
        var nulacc : Account?
        nulacc=null
        database=Firebase.database.getReference("Accounts")
        database.addChildEventListener(object :ChildEventListener{

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var tmp=snapshot.getValue<Account>()
                if (isTrue) {
                    if(account.mUsername== tmp?.mUsername){
                        if(account.mPassword==tmp?.mPassword) {
                            accountTmp.postValue(tmp)
                            Log.i("TEST1", tmp.toString())
                            Log.i("TEST2", accountTmp.toString())
                            isTrue = false
                        } else {
                            Log.i("TEST3", nulacc.toString())
                            accountTmp.postValue(nulacc)
                        }
                    } else {
                        Log.i("TEST4", nulacc.toString())
                        accountTmp.postValue(nulacc)
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
                Log.i("MainActivity:GetAccout",error.message)
            }

        })



        return accountTmp
    }


    override fun pushAccount(account: Account): MutableLiveData<Boolean>{

        database=Firebase.database.getReference()
        var ispush: MutableLiveData<Boolean>
        ispush= MutableLiveData()
        Log.i("TEST2", account.toString())
        database.child("Accounts").child(account.mUsername.toString()).setValue(account,
            object: DatabaseReference.CompletionListener{
                override fun onComplete(error: DatabaseError?, ref: DatabaseReference) {
                    if (error != null) {
                        ispush.postValue(true)
                    } else
                        ispush.postValue((false))
                }
            })
        return ispush
    }
    override fun isExist(account: Account):MutableLiveData<Boolean>{
        var isAccount : MutableLiveData<Boolean>
        isAccount = MutableLiveData()
        var isTrue : Boolean = true
        database=Firebase.database.getReference("Accounts")
        database.addChildEventListener(object :ChildEventListener{

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var tmp = snapshot.getValue<Account>()
                if (isTrue) {
                    if (account.mUsername == tmp?.mUsername) {
                        isAccount.postValue(true)
                        isTrue = false

                    } else {
                        isAccount.postValue(false)
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
                Log.i("MainActivity:GetAccout",error.message)
            }

        })
        return isAccount
    }

    override fun getAccout(username: String) : MutableLiveData<Account> {
        var accountTmp : MutableLiveData<Account>
        accountTmp = MutableLiveData()
        database=Firebase.database.getReference("Accounts")
        database.addChildEventListener(object :ChildEventListener{

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var tmp=snapshot.getValue<Account>()
                if(username== tmp?.mUsername){
                    accountTmp.postValue(tmp)
                    Log.i("TEST2", accountTmp.toString())
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

    override fun getProductType(): MutableLiveData<List<String>>{
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

    override fun getAllProduct(): MutableLiveData<List<Product>> {
        var mutableLiveData : MutableLiveData<List<Product>> = MutableLiveData()
        var list= ArrayList<Product>()
        database= Firebase.database.getReference("Products")
        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(productSnapshot in snapshot.children){
                        val product= productSnapshot.getValue(Product::class.java)
                        list.add(product!!)
                    }
                }
                mutableLiveData.postValue(list)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return mutableLiveData
    }

    override fun getListProduct(type: String): MutableLiveData<List<Product>> {
        var mutableLiveData: MutableLiveData<List<Product>> = MutableLiveData()
        var list= ArrayList<Product>()
        database= Firebase.database.getReference("Products")
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(Product::class.java)
                        if (product?.mType == type) {
                            list.add(product)
                        }
                    }
                    mutableLiveData.postValue(list)
                }
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return mutableLiveData
    }

    override fun search(nameProduct: String): MutableLiveData<List<Product>> {
        var mutableLiveData: MutableLiveData<List<Product>> = MutableLiveData()
        var list= ArrayList<Product>()
        database= Firebase.database.getReference("Products")
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(productSnapshot in snapshot.children){
                        val product = productSnapshot.getValue(Product::class.java)
                        if(product?.mName?.replace("\\s".toRegex(), "")?.lowercase() == nameProduct.replace("\\s".toRegex(), "").lowercase()){
                            list.add(product)
                        }
                    }
                    if(list.isEmpty()){
                        mutableLiveData.postValue(null)
                    }
                    else{
                        mutableLiveData.postValue(list)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return mutableLiveData
    }

    override fun addCart(product: Product, username: String) {
        database= FirebaseDatabase.getInstance().getReference("Cart")
        var id= database.push().key
        database.child(username).child(product.mID.toString()).setValue(product)
    }

    override fun getCart(username: String): MutableLiveData<Cart> {
        var mutableLiveData: MutableLiveData<Cart> = MutableLiveData()
        var list= ArrayList<Product>()
        database= FirebaseDatabase.getInstance().getReference("Cart").child(username)
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(productSnapshot in snapshot.children){
                        val product= productSnapshot.getValue(Product::class.java)
                        list.add(product!!)
                    }
                    var cart = Cart(list)
                    mutableLiveData.postValue(cart)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return mutableLiveData

    }

    override fun setUser(username: String, account: Account) {
        Log.i("FirebaseDatabase",username.toString())
        Log.i("FirebaseDatabase",account.toString())
        database= Firebase.database.getReference("Accounts")
        database.child(username).updateChildren(account.toMap())
    }

    override fun deleteProductInCart(username: String, productId: String) {
        TODO("Not yet implemented")
    }
}