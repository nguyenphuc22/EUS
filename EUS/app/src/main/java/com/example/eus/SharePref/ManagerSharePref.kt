package com.example.eus.SharePref

import android.app.Activity
import android.content.Context

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
}