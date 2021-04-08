package com.mary.raidfarmingprogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.mary.raidfarmingprogram.constant.Constant
import com.mary.raidfarmingprogram.constant.SensitiveInfo
import com.mary.raidfarmingprogram.util.ViewUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewUtil.setStatusBarColor(this, R.color.black)

        var gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(SensitiveInfo.GOOGLE_SIGN_IN_TOKEN)
                .requestEmail()
                .build()

        var googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    override fun onStart() {
        var account : GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        super.onStart()
    }
}