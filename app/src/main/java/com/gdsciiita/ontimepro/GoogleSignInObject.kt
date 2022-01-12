package com.gdsciiita.ontimepro

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

//singleton to access Google Sign In
abstract class GoogleSignInObject {
    companion object {
        @Volatile
        private var INSTANCE: GoogleSignInClient? = null
        fun getSignInClient(context: Context): GoogleSignInClient {
            return INSTANCE ?: synchronized(this) {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestServerAuthCode(context.applicationContext.getString(R.string.default_web_client_id))
                    .requestIdToken(context.applicationContext.getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()
                // Build a GoogleSignInClient with the options specified by gso.
                val instance = GoogleSignIn.getClient(context.applicationContext, gso)
                INSTANCE = instance
                return instance
            }
        }
    }
}