package com.valsgroup.fleetx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.valsgroup.fleetx.screens.SplashScreen
import com.valsgroup.fleetx.navigation.NavigationManager
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                delay(2000)
                when {
                    !NavigationManager.isOnboardingCompleted(context) -> {
                        NavigationManager.navigateToOnboarding(context)
                    }
                    NavigationManager.isLoggedIn(context) -> {
                        NavigationManager.navigateToMainScreen(context)
                    }
                    else -> {
                        NavigationManager.navigateToAuth(context)
                    }
                }
            }
            SplashScreen()
        }
    }
} 