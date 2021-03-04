package com.mx.bajun.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.mx.bajun.utils.Constants.EMPTY_INT
import com.mx.bajun.utils.Constants.EMPTY_STRING

class BajunSharedPreferences private constructor (){

    fun setString(context : Context?, key:String, value: String) {
        val editor : Editor? = getEditor(context)
        editor?.putString(key, value)
        editor?.apply()
    }

    fun getString(context: Context?, key: String) : String {
        val prefs : SharedPreferences? = context?.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
        return prefs?.getString(key, EMPTY_STRING)?.toString() ?: EMPTY_STRING
    }

    fun setInt(context: Context?, key: String, value:Int) {
        val editor : Editor? = getEditor(context)
        editor?.putInt(key, value)
        editor?.apply()
    }

    fun getInt(context: Context?, key:String) : Int {
        val prefs : SharedPreferences? = context?.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
        return prefs?.getInt(key, EMPTY_INT) ?: EMPTY_INT
    }

    private fun getEditor(context: Context?) : Editor? {
        val prefs : SharedPreferences? = context?.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
        return prefs?.edit()
    }

    companion object {
        private const val SHARED_PREFERENCES_KEY = "com.mx.bajun"
        private var INSTANCE : BajunSharedPreferences? = null
        val instance : BajunSharedPreferences
            get() {
                if (INSTANCE == null) {
                    INSTANCE = BajunSharedPreferences()
                }
                return INSTANCE as BajunSharedPreferences
            }
    }
}