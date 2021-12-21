package com.example.eus.SharePref

import android.app.Activity

interface SharedPref {
    fun setState(activity: Activity?,state:String)
    fun getState(activity: Activity?):String?
    fun setAccount(activity: Activity?,account:String)
    fun getAccount(activity: Activity?): String?

}