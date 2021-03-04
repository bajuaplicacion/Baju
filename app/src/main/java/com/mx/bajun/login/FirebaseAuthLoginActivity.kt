package com.mx.bajun.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.mx.bajun.R
import com.mx.bajun.base.BaseActivity
import com.mx.bajun.homescreen.HomeScreenActivity
import com.mx.bajun.utils.Common.Companion.isValidEmail
import com.mx.bajun.utils.Constants
import com.mx.bajun.utils.Constants.CREATE_ACCOUNT_RESULT_ID
import com.mx.bajun.utils.Constants.FAILURE_ID
import com.mx.bajun.utils.Constants.SUCCESS_ID

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CREATE_ACCOUNT_RESULT_ID -> {
                Log.d(TAG, "onActivityResult - create account")
                when (resultCode) {
                    SUCCESS_ID -> setResult(SUCCESS_ID)
                    FAILURE_ID -> setResult(FAILURE_ID)
                }
                finish()
            }
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
            resetError()
        }
    }

    private fun goToHomeScreen(displayName : String?, email : String?) {
        val homeScreenIntent : Intent = Intent(this, HomeScreenActivity::class.java).apply {
            putExtra(Constants.USER_DISPLAY_NAME_KEY, displayName)
            putExtra(Constants.USER_EMAIL_KEY, email)
        }
        startActivity(homeScreenIntent)
    }

    private fun goToCreaCuenta() {
        val crearCuentaIntent : Intent = Intent(this, CreateAccountActivity::class.java)
        startActivityForResult(crearCuentaIntent, CREATE_ACCOUNT_RESULT_ID)
    }

    private fun errorMessage(message : String) {
        tvErrorMessage.text = message
        tvErrorMessage.visibility = View.VISIBLE
        etCorreo.setBackgroundResource(R.drawable.error_background)
        btnIngresar.isEnabled = false
    }

    private fun resetError() {
        tvErrorMessage.visibility = View.INVISIBLE
        etCorreo.setBackgroundResource(R.drawable.white_background)
        btnIngresar.isEnabled = true
    }

    private fun login() {

    }

    companion object {
        const val TAG : String = "FBALoginActivity"
    }
}