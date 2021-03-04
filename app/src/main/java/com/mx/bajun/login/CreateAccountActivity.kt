package com.mx.bajun.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.mx.bajun.R
import com.mx.bajun.base.BaseActivity
import com.mx.bajun.utils.BajunSharedPreferences
import com.mx.bajun.utils.Common.Companion.isValidEmail
import com.mx.bajun.utils.Constants.EMAIL_SIGN_IN_TYPE
import com.mx.bajun.utils.Constants.EMPTY_STRING
import com.mx.bajun.utils.Constants.SIGN_IN_TYPE_KEY
import com.mx.bajun.utils.Constants.SUCCESS_ID

class CreateAccountActivity : BaseActivity(), View.OnClickListener, View.OnFocusChangeListener {

    private lateinit var tvCcMensajeDeError : TextView
    private lateinit var etCcNombre : EditText
    private lateinit var etCcApellido : EditText
    private lateinit var etCcCorreo : EditText
    private lateinit var etCcContrasena : EditText
    private lateinit var etCcVerifica : EditText
    private lateinit var btnCreaCuenta : Button
    private lateinit var auth: FirebaseAuth
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
                    val firstName : String = etCcNombre.text.toString()
                    val lastName : String = etCcApellido.text.toString()
                    val email : String = etCcCorreo.text.toString()
                    val password : String = etCcContrasena.text.toString()
                    resetError(0)
                    createAccount(email, password, firstName, lastName)
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
        auth = FirebaseAuth.getInstance()
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
        tvCcMensajeDeError.text = EMPTY_STRING
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

    private fun createAccount(email:String, password : String, firstName : String, lastName : String) {
        val sb : StringBuffer = StringBuffer()
        sb.append(firstName)
        sb.append(" ")
        sb.append(lastName)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, OnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createAccount - Success")
                    updateDisplayName(sb.toString())
                } else {
                    when (task.exception) {
                        is FirebaseAuthWeakPasswordException -> {
                            errorMessage(getString(R.string.error_contrasenia_debil), R.id.et_cc_verifica)
                            Log.d(TAG, "createAccount - FirebaseAuthWeakPasswordException")
                        }
                        is FirebaseAuthUserCollisionException -> {
                            errorMessage(getString(R.string.error_correo_existente), R.id.et_cc_correo)
                            Log.d(TAG, "createAccount - FirebaseAuthUserCollisionException")
                        }
                        is FirebaseAuthInvalidCredentialsException -> {
                            Log.d(TAG, "createAccount - FirebaseAuthInvalidCredentialsException")
                        }
                    }
                }
            })
    }

    private fun updateDisplayName(displayName : String) {
        val user = auth.currentUser
        val profileUpdate : UserProfileChangeRequest = UserProfileChangeRequest.Builder().setDisplayName(displayName).build()
        user?.updateProfile(profileUpdate)
            ?.addOnCompleteListener(OnCompleteListener { task: Task<Void> ->
                if (task.isSuccessful) {
                    Log.d(TAG, "updateDisplayName: successful")
                    BajunSharedPreferences.instance.setString(this, SIGN_IN_TYPE_KEY, EMAIL_SIGN_IN_TYPE)
                    setResult(SUCCESS_ID)
                    finish()
                } else {
                    Log.d(TAG, "updateDisplayName: failuer")
                }
            })
    }

    companion object {
        const val TAG : String = "CreateAccountActivity"
    }

}