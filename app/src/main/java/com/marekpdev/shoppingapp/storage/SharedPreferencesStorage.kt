package com.marekpdev.shoppingapp.storage

import android.content.Context
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
class SharedPreferencesStorage @Inject constructor(context: Context): Storage {

    // todo is this name okay ? or should i use default shared pref
    private val sharedPreferences = context.getSharedPreferences("shoppingapp", Context.MODE_PRIVATE)

    override fun setString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }
}