package dev.olaore.recipeze.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object Prefs {

    private const val PIN_KEY = "pin"
    private const val AUTHENTICATED_KEY = "authenticated"
    private const val RECIPEZE_PREFERENCES_KEY = "recipeze_preferences"

    fun authenticateUser(ctx: Context, pin: String) {
        val sharedPreferences = getPreferences(ctx)
        sharedPreferences.edit(commit = true) {
            putBoolean(AUTHENTICATED_KEY, true)
            putString(PIN_KEY, pin)
        }
    }

    fun isAuthenticated(ctx: Context): Boolean {
        return getPreferences(ctx).getBoolean(AUTHENTICATED_KEY, false)
    }

    private fun getPreferences(ctx: Context): SharedPreferences {
        return ctx.getSharedPreferences(RECIPEZE_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

}