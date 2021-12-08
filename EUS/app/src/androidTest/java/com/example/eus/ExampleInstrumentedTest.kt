package com.example.eus


import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.eus.FirebaseApi.FirebaseDatabaseRealTime
import com.example.eus.ODT.Account

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.eus", appContext.packageName)
    }
    @Test
    fun testDatabase() {
        val firebaseDatabase = FirebaseDatabaseRealTime()
        var account = Account.Builder()
            .addId("2")
            .addUsername("Phuc VR")
            .addPassword("123")
            .build()
        Log.i("Test",firebaseDatabase.getAccount(account = account).toString())
    }
}