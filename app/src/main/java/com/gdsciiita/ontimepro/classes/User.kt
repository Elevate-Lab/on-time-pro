package com.gdsciiita.ontimepro.classes

import android.content.ContentValues.TAG
import android.util.Log

object User{

    init {
        Log.i(TAG, "Singleton class invoked.")
    }
    lateinit var userName: String
    lateinit var authToken: String
    lateinit var userEmail: String
    lateinit var firebaseToken: String

}