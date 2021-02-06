package com.mx.bajun.utils

import android.text.TextUtils
import androidx.core.util.PatternsCompat

class Common {
    companion object {
        fun isValidEmail(sEmail : String):Boolean {
            return (!TextUtils.isEmpty(sEmail) && PatternsCompat.EMAIL_ADDRESS.matcher(sEmail).matches())
        }
    }
}