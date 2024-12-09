package com.example.ikanku.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.example.ikanku.model.Pembeli
import com.example.ikanku.model.Toko

object SharedPreferencesHelper {

    private const val PREF_NAME = "user_preferences"
    private const val KEY_USER_TYPE = "user_type" // "pembeli" atau "toko"
    private const val KEY_USER_DATA = "user_data"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveUser(context: Context, userType: String, user: Any) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_USER_TYPE, userType)
        editor.putString(KEY_USER_DATA, Gson().toJson(user))
        editor.apply()
    }

    fun isUserLoggedIn(context: Context): Boolean {
        return getPreferences(context).contains(KEY_USER_TYPE) &&
                getPreferences(context).contains(KEY_USER_DATA)
    }

    fun getUserType(context: Context): String? {
        return getPreferences(context).getString(KEY_USER_TYPE, null)
    }

    fun getUserData(context: Context): Any? {
        val userType = getUserType(context)
        val userDataJson = getPreferences(context).getString(KEY_USER_DATA, null)
        return when (userType) {
            "pembeli" -> Gson().fromJson(userDataJson, Pembeli::class.java)
            "toko" -> Gson().fromJson(userDataJson, Toko::class.java)
            else -> null
        }
    }

    fun logout(context: Context) {
        val editor = getPreferences(context).edit()
        editor.clear()
        editor.apply()
    }
}
