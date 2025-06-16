package com.valsgroup.fleetx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.valsgroup.fleetx.navigation.NavigationManager
import com.valsgroup.fleetx.auth.LoginFragment
import com.valsgroup.fleetx.auth.RegisterFragment
import com.valsgroup.fleetx.auth.OtpVerificationFragment
import com.valsgroup.fleetx.auth.CreatePasswordFragment

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // Check if user is already logged in
        if (NavigationManager.isLoggedIn(this)) {
            NavigationManager.navigateToMainScreen(this)
            return
        }

        // Start with login fragment
        if (savedInstanceState == null) {
            replaceFragment(LoginFragment())
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.auth_container, fragment)
            .commit()
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.auth_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
} 