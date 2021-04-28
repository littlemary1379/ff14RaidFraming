package com.mary.raidfarmingprogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
import com.mary.raidfarmingprogram.constant.Constant
import com.mary.raidfarmingprogram.constant.SensitiveInfo
import com.mary.raidfarmingprogram.util.DlogUtil
import com.mary.raidfarmingprogram.util.ViewUtil
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
        private const val logInRequestCode = 10
    }

    private lateinit var signInButton: SignInButton

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewUtil.setStatusBarColor(this, R.color.black)

        var gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(SensitiveInfo.GOOGLE_SIGN_IN_TOKEN)
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()

        findView()
        setListener()
    }

    override fun onStart() {
        var account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        super.onStart()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == logInRequestCode) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

    }

    private fun findView() {
        signInButton = findViewById(R.id.signInButton)

    }

    private fun setListener() {
        signInButton.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, logInRequestCode)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account : GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                account.id?.let {
                    DlogUtil.d(TAG, it)
                }
                account.idToken?.let { firebaseAuthWithGoogle(it) }

            }
        } catch (e: Exception) {

        }
    }

    private fun firebaseAuthWithGoogle(token : String) {
        val credential = GoogleAuthProvider.getCredential(token, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful) {
                DlogUtil.d(TAG, auth.currentUser)
            } else {
                DlogUtil.d(TAG, "error ${it.exception}")
            }
        }
    }


}