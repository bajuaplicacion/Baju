package com.mx.bajun.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mx.bajun.R
import com.mx.bajun.base.BaseActivity
import com.mx.bajun.homescreen.HomeScreenActivity
import com.mx.bajun.utils.Constants.EMAIL_LOGIN_RESULT_ID
import com.mx.bajun.utils.Constants.GOOGLE_LOGIN_REQ_ID
import com.mx.bajun.utils.Constants.USER_DISPLAY_NAME_TAG
import com.mx.bajun.utils.Constants.USER_EMAIL_TAG

class LoginHomeScreenActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpToolbar(false, false)
        setGoogleSignIn()
        setFirebaseAuthentication()
    }

    override fun onStart() {
        super.onStart()
        //Google account
        val account : GoogleSignInAccount?  = GoogleSignIn.getLastSignedInAccount(this)
        val auth : FirebaseAuth = Firebase.auth
        val currentUser = auth.currentUser
        if (account != null) {
            goToHomeScreen(account.displayName, account.email)
        }  else if (currentUser != null) {
            goToHomeScreen(currentUser.displayName, currentUser.email)
        } else {
            //TODO Not login
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GOOGLE_LOGIN_REQ_ID -> {
                try {
                    val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
                    handleSignInResult(task)
                } catch (e : ApiException) {
                    Log.d(TAG, "Google sign in failed")
                }
            }
            EMAIL_LOGIN_RESULT_ID -> {
                Log.d(TAG, "Email login")
            }
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.sign_in_button -> googleSignIn()
            R.id.btn_emailPasswordAuth -> goToFirebaseAuthLogin()
        }
    }

    //Google sign in set up
    private fun setGoogleSignIn() {
        val signInButton : SignInButton = findViewById(R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_WIDE)
        signInButton.setOnClickListener(this)
    }

    //Firebase authentication set
    private fun setFirebaseAuthentication() {
        val btn_emailPasswordAuth : Button = findViewById(R.id.btn_emailPasswordAuth)
        btn_emailPasswordAuth.setOnClickListener(this)
    }

    private fun googleSignIn() {
        val gso : GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val mGoogleSignInClient : GoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent : Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_LOGIN_REQ_ID)
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account : GoogleSignInAccount = task.getResult(ApiException::class.java)
            goToHomeScreen(account.displayName, account.email)
        } catch (e : ApiException) {
            Log.w(ActivityConstants.TAG, "signInResult:failed code= " + e.status)
        }
    }

    private fun goToHomeScreen(displayName : String?, email : String?) {
        val homeScreenIntent : Intent = Intent(this, HomeScreenActivity::class.java).apply {
            putExtra(USER_DISPLAY_NAME_TAG, displayName)
            putExtra(USER_EMAIL_TAG, email)
        }
        startActivity(homeScreenIntent)
    }

    private fun goToFirebaseAuthLogin() {
        val firebaseAuthLoginIntent : Intent = Intent(this, FirebaseAuthLoginActivity::class.java)
        startActivityForResult(firebaseAuthLoginIntent, EMAIL_LOGIN_RESULT_ID)
    }

    companion object ActivityConstants {
        const val TAG : String = "LoginActivity"
    }
}