package com.valsgroup.fleetx.navigation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.valsgroup.fleetx.AuthActivity
import com.valsgroup.fleetx.MainActivity
import com.valsgroup.fleetx.OnboardingActivity

import android.widget.Toast

object NavigationManager {
    private const val PREFS_NAME = "fleetx_prefs"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"
    private const val KEY_USER_EMAIL = "user_email"
    private const val KEY_USER_PASSWORD = "user_password"
    private const val KEY_USER_NAME = "user_name"
    private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"
    private const val KEY_ONBOARDED_EMAILS = "onboarded_emails"
    private const val KEY_HAS_REGISTERED_USER = "has_registered_user"

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

    fun isOnboardingCompleted(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_ONBOARDING_COMPLETED, false)
    }

    fun setOnboardingCompleted(context: Context) {
        getPreferences(context).edit().putBoolean(KEY_ONBOARDING_COMPLETED, true).apply()
    }

    fun hasEmailSeenOnboarding(context: Context, email: String): Boolean {
        val onboardedEmails = getPreferences(context).getStringSet(KEY_ONBOARDED_EMAILS, setOf()) ?: setOf()
        return onboardedEmails.contains(email)
    }

    fun markEmailAsOnboarded(context: Context, email: String) {
        val prefs = getPreferences(context)
        val onboardedEmails = prefs.getStringSet(KEY_ONBOARDED_EMAILS, setOf())?.toMutableSet() ?: mutableSetOf()
        onboardedEmails.add(email)
        prefs.edit().putStringSet(KEY_ONBOARDED_EMAILS, onboardedEmails).apply()
    }

    fun shouldShowOnboarding(context: Context, email: String? = null): Boolean {
        // Show onboarding if:
        // 1. Device hasn't completed onboarding (first installation), OR
        // 2. New email is detected (new user signing in)
        val deviceOnboardingCompleted = isOnboardingCompleted(context)
        val emailSeenOnboarding = email?.let { hasEmailSeenOnboarding(context, it) } ?: true
        
        return !deviceOnboardingCompleted || !emailSeenOnboarding
    }

    fun hasRegisteredUser(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_HAS_REGISTERED_USER, false)
    }

    fun setHasRegisteredUser(context: Context) {
        getPreferences(context).edit().putBoolean(KEY_HAS_REGISTERED_USER, true).apply()
    }

    fun navigateToOnboarding(context: Context) {
        val intent = Intent(context, OnboardingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

    fun logout(context: Context) {
        val prefs = getPreferences(context)
        prefs.edit().apply {
            // Only remove the login status - keep user registration data
            remove(KEY_IS_LOGGED_IN)
            apply()
        }
        navigateToAuth(context)
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