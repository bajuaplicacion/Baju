package com.mx.bajun.login

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.mx.bajun.R
import com.mx.bajun.base.BaseActivity
import com.mx.bajun.utils.Common.Companion.isValidEmail
import com.mx.bajun.utils.Constants.STRING_VACIO

class CreateAccountActivity : BaseActivity(), View.OnClickListener, View.OnFocusChangeListener {

    private lateinit var tvCcMensajeDeError : TextView
    private lateinit var etCcNombre : EditText
    private lateinit var etCcApellido : EditText
    private lateinit var etCcCorreo : EditText
    private lateinit var etCcContrasena : EditText
    private lateinit var etCcVerifica : EditText
    private lateinit var btnCreaCuenta : Button
    private var esCorreoCorrecto : Boolean = false
    private var esContrasenaCorrecta : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        init()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_crea_cuenta -> {
                if (isDataAccountComplete()) {
                    resetError(0)
                    createAccount()
                } else {
                    errorMessage("Datos incompletos", 0)
                }
            }
        }
    }

    override fun onFocusChange(p0: View?, hasFocus: Boolean) {
        when (p0?.id) {
            R.id.et_cc_correo ->  {
                if (!hasFocus) {
                    val correo: String = etCcCorreo.text.toString()
                    if (!TextUtils.isEmpty(correo) && !isValidEmail(correo)) {
                        errorMessage("Correo incorrecto", R.id.et_cc_correo)
                    } else {
                        resetError(R.id.et_cc_correo)
                    }
                }
            }
            R.id.et_cc_verifica -> {
                if (!hasFocus) {
                    val contrasena: String = etCcContrasena.text.toString()
                    val verifica: String = etCcVerifica.text.toString()
                    if ( TextUtils.isEmpty(contrasena) || TextUtils.isEmpty(verifica) ) {
                        errorMessage("Contraseña incorrecta", R.id.et_cc_verifica)
                    } else {
                        if (contrasena != verifica) {
                            errorMessage("Contraseña incorrecta", R.id.et_cc_verifica)
                        } else {
                            resetError(R.id.et_cc_verifica)
                        }
                    }
                }
            }
        }
    }

    private fun init() {
        tvCcMensajeDeError = findViewById(R.id.tv_ccMensajeDeError)
        etCcNombre = findViewById(R.id.et_cc_nombre)
        etCcApellido = findViewById(R.id.et_cc_apellido)
        etCcCorreo = findViewById(R.id.et_cc_correo)
        etCcContrasena = findViewById(R.id.et_cc_contrasena)
        etCcVerifica = findViewById(R.id.et_cc_verifica)
        btnCreaCuenta = findViewById(R.id.btn_crea_cuenta)
        etCcCorreo.onFocusChangeListener = this
        etCcVerifica.onFocusChangeListener = this
        btnCreaCuenta.setOnClickListener(this)
    }

    private fun errorMessage(mensajeError : String, id : Int) {
        tvCcMensajeDeError.text = mensajeError
        tvCcMensajeDeError.visibility = View.VISIBLE
        when (id) {
            R.id.et_cc_correo -> {
                etCcCorreo.setBackgroundResource(R.drawable.error_background)
                esCorreoCorrecto = false
            }
            R.id.et_cc_verifica -> {
                etCcContrasena.setBackgroundResource(R.drawable.error_background)
                etCcVerifica.setBackgroundResource(R.drawable.error_background)
                esContrasenaCorrecta = false
            }
        }
    }

    private fun resetError(id: Int) {
        tvCcMensajeDeError.text = STRING_VACIO
        tvCcMensajeDeError.visibility = View.INVISIBLE
        when (id) {
            R.id.et_cc_correo -> {
                etCcCorreo.setBackgroundResource(R.drawable.white_background)
                esCorreoCorrecto = true
            }
            R.id.et_cc_verifica -> {
                etCcContrasena.setBackgroundResource(R.drawable.white_background)
                etCcVerifica.setBackgroundResource(R.drawable.white_background)
                esContrasenaCorrecta = true
            }
        }
    }

    private fun isDataAccountComplete() : Boolean {
        return esCorreoCorrecto && esContrasenaCorrecta
                && !TextUtils.isEmpty(etCcNombre.text.toString())
                && !TextUtils.isEmpty(etCcApellido.text.toString())
    }

    private fun createAccount() {

    }

}