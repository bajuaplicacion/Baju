package com.mx.bajun.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mx.bajun.R
import com.mx.bajun.base.BaseActivity

class CreateAccountActivity : BaseActivity(), View.OnClickListener, View.OnFocusChangeListener {

    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        init()
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun onFocusChange(p0: View?, p1: Boolean) {
        TODO("Not yet implemented")
    }

    private fun init() {

    }

}