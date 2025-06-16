package com.valsgroup.fleetx.navigation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.valsgroup.fleetx.AuthActivity
import com.valsgroup.fleetx.MainActivity

import android.widget.Toast

object NavigationManager {
    private const val PREFS_NAME = "fleetx_prefs"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"
    private const val KEY_USER_EMAIL = "user_email"
    private const val KEY_USER_PASSWORD = "user_password"
    private const val KEY_USER_NAME = "user_name"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun isLoggedIn(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun setLoggedIn(context: Context, isLoggedIn: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun navigateToMainScreen(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

    fun navigateToAuth(context: Context) {
        val intent = Intent(context, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

    fun saveUserCredentials(context: Context, name: String, email: String, password: String) {
        getPreferences(context).edit().apply {
            putString(KEY_USER_NAME, name)
            putString(KEY_USER_EMAIL, email)
            putString(KEY_USER_PASSWORD, password)
            apply()
        }
    }

    fun verifyCredentials(context: Context, email: String, password: String): Boolean {
        val prefs = getPreferences(context)
        val savedEmail = prefs.getString(KEY_USER_EMAIL, "")
        val savedPassword = prefs.getString(KEY_USER_PASSWORD, "")
        return email == savedEmail && password == savedPassword
    }

    fun getUserEmail(context: Context): String? {
        return getPreferences(context).getString(KEY_USER_EMAIL, null)
    }

    fun getUserName(context: Context): String? {
        return getPreferences(context).getString(KEY_USER_NAME, null)
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    // Comment out unused features for now
    /*
    fun navigateToProfile(context: Context) {
        // TODO: Implement profile navigation
    }

    fun navigateToSettings(context: Context) {
        // TODO: Implement settings navigation
    }

    fun navigateToFleetManagement(context: Context) {
        // TODO: Implement fleet management navigation
    }

    fun navigateToTracking(context: Context) {
        // TODO: Implement tracking navigation
    }

    fun logout(context: Context) {
        setLoggedIn(context, false)
        getPreferences(context).edit().clear().apply()
        navigateToAuth(context)
    }
    */
}