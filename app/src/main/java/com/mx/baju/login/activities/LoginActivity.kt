package com.mx.baju.login.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mx.baju.R
import com.mx.baju.activities.BaseActivity

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_login)
        super.onCreate(savedInstanceState)
        showBackNavigationButton(false)
    }

    override fun onResume() {
        super.onResume()
        showMenu(false)
    }
}