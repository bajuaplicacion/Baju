package com.mx.bajun.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import com.mx.bajun.R
import com.mx.bajun.base.BaseActivity
import com.mx.bajun.homescreen.HomeScreenActivity
import com.mx.bajun.utils.Constants

class FirebaseAuthLoginActivity : BaseActivity(), View.OnClickListener, View.OnFocusChangeListener {

    private lateinit var etCorreo : EditText
    private lateinit var btnIngresar : Button
    private lateinit var tvCrearCuenta : TextView
    private lateinit var tvErrorMessage : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_auth_login)
        init()
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

    private fun init() {
        setUpToolbar(true, false)
        etCorreo = findViewById(R.id.et_correo)
        btnIngresar  = findViewById(R.id.btn_ingresar)
        tvCrearCuenta = findViewById(R.id.tv_crearCuenta)
        tvErrorMessage = findViewById(R.id.tv_errorMessage)
        etCorreo.onFocusChangeListener = this
        btnIngresar.setOnClickListener(this)
        btnIngresar.isEnabled = false
        tvCrearCuenta.setOnClickListener(this)
    }

    private fun checkEmailFormat() {
        val sEmail : String = etCorreo.text.toString()
        if (!isValidEmail(sEmail)) {
            errorMessage( getString(R.string.error_email_format) )
        } else {
            tvErrorMessage.visibility = View.INVISIBLE
            etCorreo.setBackgroundResource(R.drawable.white_background)
            btnIngresar.isEnabled = true
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
        tvErrorMessage.text = message
        tvErrorMessage.visibility = View.VISIBLE
        etCorreo.setBackgroundResource(R.drawable.error_background)
        btnIngresar.isEnabled = false
    }

    private fun login() {

    }
}