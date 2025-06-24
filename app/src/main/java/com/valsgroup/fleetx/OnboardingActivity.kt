package com.valsgroup.fleetx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.valsgroup.fleetx.screens.OnboardingScreen
import com.valsgroup.fleetx.navigation.NavigationManager
import com.valsgroup.fleetx.ui.theme.FleetXTheme

class OnboardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleetXTheme {
                val context = LocalContext.current
                OnboardingScreen(
                    onFinished = {
                        NavigationManager.setOnboardingCompleted(context)
                        NavigationManager.navigateToAuth(context)
                    }
                )
            }
        }
    }
} 