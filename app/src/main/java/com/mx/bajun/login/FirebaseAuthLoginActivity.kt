package com.mx.bajun.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import com.mx.bajun.R
import com.mx.bajun.base.BaseActivity

class FirebaseAuthLoginActivity : BaseActivity(), View.OnClickListener, View.OnFocusChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_auth_login)
        setUpUI()
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun onFocusChange(p0: View?, hasFocus: Boolean) {
        when (p0?.id) {
            R.id.et_correo -> if (!hasFocus)  checkEmailFormat()
        }
    }

    private fun setUpUI() {
        setUpToolbar(true, false)
        val et_correo : EditText = findViewById(R.id.et_correo)
        et_correo.onFocusChangeListener = this
    }

    private fun checkEmailFormat() {
        Toast.makeText(this, "leave edit text", Toast.LENGTH_SHORT).show()
        val et_correo : EditText = findViewById(R.id.et_correo)
        val tv_errorMessage : TextView = findViewById(R.id.tv_errorMessage)
        val sEmail : String = et_correo.text.toString()
        if (!isValidEmail(sEmail)) {
            tv_errorMessage.text = getString(R.string.email_format_error)
            tv_errorMessage.visibility = View.VISIBLE
            et_correo.setBackgroundResource(R.drawable.error_background)
        } else {
            tv_errorMessage.visibility = View.INVISIBLE
            et_correo.setBackgroundResource(R.drawable.white_background)
        }
    }

    private fun isValidEmail(sEmail : String):Boolean {
        return (!TextUtils.isEmpty(sEmail) && EMAIL_ADDRESS.matcher(sEmail).matches())
    }

}