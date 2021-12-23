package com.example.eus.SharePref

import android.app.Activity
import android.content.Context
import com.example.eus.ODT.ShipInfo

class ManagerSharePref : SharedPref {
    override fun setState(activity:Activity?,state:String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        if (sharedPref != null) {
            with (sharedPref.edit()) {
                putString("isLogin",state)

                apply()
            }
        }
    }


    override fun getState(activity: Activity?): String? {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        if (sharedPref != null) {
           return sharedPref.getString("isLogin","false")
        }
        return "get failed"
    }


    override fun setAccount(activity: Activity?,account:String){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        if (sharedPref != null) {
            with (sharedPref.edit()) {
                putString("username",account)

                apply()
            }
        }

    }
    override fun getAccount(activity: Activity?): String? {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        if (sharedPref != null) {
            return sharedPref.getString("username","false")
        }
        return "get failed"
    }

    override fun getShipInfo(activity: Activity?): ShipInfo? {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        var shipInfo = ShipInfo.Builder()
        if (sharedPref != null) {
            sharedPref.getString("username_ShipInfo","Null")?.let {
                shipInfo.addName(it) }
            sharedPref.getString("phone_ShipInfo","Null")?.let {
                shipInfo.addPhone(it) }
            sharedPref.getString("address_ShipInfo","Null")?.let {
                shipInfo.addAddress(it) }
            return shipInfo.build()
        }
        return null
    }

    override fun setShipInfo(activity: Activity?, shipInfo: ShipInfo) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        if (sharedPref != null) {
            with (sharedPref.edit()) {
                putString("username_ShipInfo",shipInfo.name)
                putString("phone_ShipInfo",shipInfo.phone)
                putString("address_ShipInfo",shipInfo.address)
                apply()
            }
        }
    }

    override fun logout(activity: Activity?) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        sharedPref?.edit()?.clear()?.apply()


    }
}