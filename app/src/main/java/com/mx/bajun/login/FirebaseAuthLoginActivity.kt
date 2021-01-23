package com.mx.bajun.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mx.bajun.R
import com.mx.bajun.base.BaseActivity

class FirebaseAuthLoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_auth_login)
        showBackNavigationButton(true)
        showMenu(false)
    }
}