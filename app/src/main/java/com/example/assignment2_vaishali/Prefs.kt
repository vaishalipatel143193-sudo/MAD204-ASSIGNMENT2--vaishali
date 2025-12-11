package com.example.assignment2_vaishali

import android.content.Context
import android.content.SharedPreferences

object Prefs {
    private const val PREFS_NAME = "assignment2_prefs"
    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    var isLoggedIn: Boolean
        get() = prefs.getBoolean("isLoggedIn", false)
        set(v) = prefs.edit().putBoolean("isLoggedIn", v).apply()

    var email: String?
        get() = prefs.getString("email", null)
        set(v) = prefs.edit().putString("email", v).apply()

    var name: String?
        get() = prefs.getString("name", null)
        set(v) = prefs.edit().putString("name", v).apply()

    var program: String?
        get() = prefs.getString("program", null)
        set(v) = prefs.edit().putString("program", v).apply()

    var age: Int
        get() = prefs.getInt("age", 0)
        set(v) = prefs.edit().putInt("age", v).apply()

    var darkMode: Boolean
        get() = prefs.getBoolean("darkMode", false)
        set(v) = prefs.edit().putBoolean("darkMode", v).apply()

    var notifications: Boolean
        get() = prefs.getBoolean("notifications", true)
        set(v) = prefs.edit().putBoolean("notifications", v).apply()

    fun clearAll() {
        prefs.edit().clear().apply()
    }
}