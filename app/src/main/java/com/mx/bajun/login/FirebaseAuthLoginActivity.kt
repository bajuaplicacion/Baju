package com.mx.bajun.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import com.mx.bajun.R
import com.mx.bajun.base.BaseActivity
import com.mx.bajun.homescreen.HomeScreenActivity
import com.mx.bajun.utils.Constants

class FirebaseAuthLoginActivity : BaseActivity(), View.OnClickListener, View.OnFocusChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_auth_login)
        setUpUI()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_ingresar -> login()
            R.id.tv_crearCuenta -> goToCreaCuenta()
        }
    }

    override fun onFocusChange(p0: View?, hasFocus: Boolean) {
        when (p0?.id) {
            R.id.et_correo -> if (!hasFocus)  checkEmailFormat()
        }
    }

    private fun setUpUI() {
        setUpToolbar(true, false)
        val et_correo : EditText = findViewById(R.id.et_correo)
        val btn_ingresar : Button = findViewById(R.id.btn_ingresar)
        val tv_crearCuenta : TextView = findViewById(R.id.tv_crearCuenta)
        et_correo.onFocusChangeListener = this
        btn_ingresar.setOnClickListener(this)
        btn_ingresar.isEnabled = false
        tv_crearCuenta.setOnClickListener(this)
    }

    private fun checkEmailFormat() {
        val et_correo : EditText = findViewById(R.id.et_correo)
        val tv_errorMessage : TextView = findViewById(R.id.tv_errorMessage)
        val btn_ingresar : Button = findViewById(R.id.btn_ingresar)
        val sEmail : String = et_correo.text.toString()
        if (!isValidEmail(sEmail)) {
            errorMessage( getString(R.string.email_format_error) )
        } else {
            tv_errorMessage.visibility = View.INVISIBLE
            et_correo.setBackgroundResource(R.drawable.white_background)
            btn_ingresar.isEnabled = true
        }
    }

    private fun isValidEmail(sEmail : String):Boolean {
        return (!TextUtils.isEmpty(sEmail) && EMAIL_ADDRESS.matcher(sEmail).matches())
    }

    private fun goToHomeScreen(displayName : String?, email : String?) {
        val homeScreenIntent : Intent = Intent(this, HomeScreenActivity::class.java).apply {
            putExtra(Constants.USER_DISPLAY_NAME_TAG, displayName)
            putExtra(Constants.USER_EMAIL_TAG, email)
        }
        startActivity(homeScreenIntent)
    }

    private fun goToCreaCuenta() {
        val crearCuentaIntent : Intent = Intent(this, CreateAccountActivity::class.java)
        startActivity(crearCuentaIntent)
    }

    private fun errorMessage(message : String) {
        val et_correo : EditText = findViewById(R.id.et_correo)
        val tv_errorMessage : TextView = findViewById(R.id.tv_errorMessage)
        val btn_ingresar : Button = findViewById(R.id.btn_ingresar)
        tv_errorMessage.text = message
        tv_errorMessage.visibility = View.VISIBLE
        et_correo.setBackgroundResource(R.drawable.error_background)
        btn_ingresar.isEnabled = false
    }

    private fun login() {

    }


}