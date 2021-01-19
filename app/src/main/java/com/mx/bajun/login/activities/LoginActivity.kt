package com.mx.bajun.login.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.mx.bajun.R
import com.mx.bajun.activities.BaseActivity

class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        showBackNavigationButton(false)
        showMenu(false)
        setGoogleSignIn()
    }

    override fun onStart() {
        super.onStart()
        val account : GoogleSignInAccount?  = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            goToHomescreen()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.sign_in_button -> signIn()
        }
    }

    private fun setGoogleSignIn() {
        val gso : GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val mGoogleSignInClient : GoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInButton : Button = findViewById<Button>(R.id.sign_in_button)
        //signInButton.setSize(SignInButton.SIZE_STANDARD)
        signInButton.setOnClickListener(this)
    }

    private fun signIn() {
        Toast.makeText(this, "Google Sign in", Toast.LENGTH_SHORT).show()
    }

    private fun goToHomescreen() {}
}