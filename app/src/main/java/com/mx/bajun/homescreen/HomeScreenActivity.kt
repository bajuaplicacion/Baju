package com.mx.bajun.homescreen

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.mx.bajun.R
import com.mx.bajun.base.BaseActivity
import com.mx.bajun.utils.Constants.USER_DISPLAY_NAME_TAG
import com.mx.bajun.utils.Constants.USER_EMAIL_TAG

class HomeScreenActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)
        val tv_user : TextView = findViewById(R.id.tv_user)
        val mDisplayName : String? = intent.getStringExtra(USER_DISPLAY_NAME_TAG)
        val mEmail : String? = intent.getStringExtra(USER_EMAIL_TAG)
        "$mDisplayName: $mEmail".also { tv_user.text = it }

        val btnSignOut : Button = findViewById(R.id.btnSignOut)
        btnSignOut.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnSignOut -> googleSignOut()
        }
    }
}