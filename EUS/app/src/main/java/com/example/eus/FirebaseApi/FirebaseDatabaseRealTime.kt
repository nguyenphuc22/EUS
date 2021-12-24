package com.example.eus.FirebaseApi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.eus.ODT.Account
import com.example.eus.ODT.Cart
import com.example.eus.ODT.Product
import com.example.eus.ODT.ShipInfo
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
                val account = snapshot.getValue(Account::class.java)
                if(username== account?.mUsername){
                    accountTmp.postValue(account)
                    Log.i("TEST2", accountTmp.toString())
                }
                Log.i("Firebase_RealTime",account.toString())
                Log.i("Firebase_RealTime",snapshot.value.toString())
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
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
        database.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val product = snapshot.getValue(Product::class.java)
                Log.i("Child","Added")
                Log.i("Child_Added",snapshot.key.toString())
                Log.i("Child_Added",snapshot.value.toString())
                Log.i("Child_Added",product.toString())
                product?.let { list.add(it) }
                mutableLiveData.postValue(list)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val product = snapshot.getValue(Product::class.java)
                Log.i("Child","Changed")
                Log.i("Child_Changed",snapshot.value.toString())
                for (i in 0..list.size - 1) {
                    if (list.get(i).mID == product?.mID) {
                        list.removeAt(i)
                        list.add(product!!)
                    }
                }
                mutableLiveData.postValue(list)
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                Log.i("Child","Removed")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                Log.i("Child","Move")
            }

            override fun onCancelled(error: DatabaseError) {
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
        database.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val product = snapshot.getValue(Product::class.java)
                list.add(product!!)
                mutableLiveData.postValue(Cart(list))
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
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
        Log.i("Firebase",username + productId)
        database=Firebase.database.getReference("Cart").child(username).child(productId)
        database.removeValue()

    }

    override fun pushShipInfo(username: String, shipInfo: ShipInfo) {
        database= Firebase.database.getReference("Accounts")
        database.child(username).child("mshipInfos").child(shipInfo.id.toString()).setValue(shipInfo)
    }

    override fun updateShipInfo(username: String, shipInfo: ShipInfo) {
        database= Firebase.database.getReference("Accounts")
        database.child(username).child("mshipInfos").child(shipInfo.id.toString()).updateChildren(shipInfo.toMap())
    }

    override fun getShipInfo(username: String): MutableLiveData<List<ShipInfo>> {
        var mutableLiveData: MutableLiveData<List<ShipInfo>> = MutableLiveData()
        var shipInfos = ArrayList<ShipInfo>()
        database= Firebase.database.getReference("Accounts").child(username).child("mshipInfos")
        database.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val shipInfo = snapshot.getValue(ShipInfo::class.java)
                shipInfos.add(shipInfo!!)
                mutableLiveData.postValue(shipInfos)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

        return mutableLiveData
    }

    override fun removeShipInfo(username: String, idShipInfo: String) {
        database= Firebase.database.getReference("Accounts")
        database.child(username).child("mshipInfos").child(idShipInfo).removeValue()
    }
}