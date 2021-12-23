package com.example.eus.SharePref

import android.app.Activity
import com.example.eus.ODT.ShipInfo

interface SharedPref {
    fun setState(activity: Activity?,state:String)
    fun getState(activity: Activity?):String?
    fun setAccount(activity: Activity?,account:String)
    fun getAccount(activity: Activity?): String?
    fun getShipInfo(activity: Activity?) : ShipInfo?
    fun setShipInfo(activity: Activity?,shipInfo: ShipInfo)
    fun logout(activity: Activity?)
}