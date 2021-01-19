package com.mx.bajun.login.activities

import android.os.Bundle
import com.mx.bajun.R
import com.mx.bajun.activities.BaseActivity

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_login)
        super.onCreate(savedInstanceState)
        showBackNavigationButton(false)
        showMenu(false)
    }

    override fun onResume() {
        super.onResume()
    }
}