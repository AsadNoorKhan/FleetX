package com.valsgroup.fleetx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.valsgroup.fleetx.navigation.NavigationManager
import com.valsgroup.fleetx.screens.HomeScreen
import com.valsgroup.fleetx.screens.FleetStatusScreen
import com.valsgroup.fleetx.screens.SettingsScreen
//import com.valsgroup.fleetx.screens.MapScreen
import com.valsgroup.fleetx.ui.theme.FleetXTheme
import com.valsgroup.fleetx.R

sealed class MainNavItem(val label: String, val iconRes: Int) {
    object Home : MainNavItem("Home", R.drawable.ic_launcher_foreground) // Replace with real icon
    object Placeholder1 : MainNavItem("Tab 2", R.drawable.ic_launcher_foreground)
    object FleetStatus : MainNavItem("Fleet Status", R.drawable.ic_launcher_foreground)
    object Placeholder2 : MainNavItem("Tab 4", R.drawable.ic_launcher_foreground)
    object Settings : MainNavItem("Settings", R.drawable.ic_launcher_foreground)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Check if user should be here
        if (!NavigationManager.isLoggedIn(this)) {
            NavigationManager.navigateToAuth(this)
            return
        }

        setContent {
            FleetXTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedItem by remember { mutableStateOf<MainNavItem>(MainNavItem.Home) }
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        bottomBar = {
            NavigationBar {
                val items = listOf(
                    MainNavItem.Home,
                    MainNavItem.Placeholder1,
                    MainNavItem.FleetStatus,
                    MainNavItem.Placeholder2,
                    MainNavItem.Settings
                )
                items.forEach { item ->
                    NavigationBarItem(
                        selected = selectedItem == item,
                        onClick = { selectedItem = item },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.iconRes),
                                contentDescription = item.label,
                                tint = if (selectedItem == item) MaterialTheme.colorScheme.primary else Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedItem) {
                is MainNavItem.Home -> HomeScreen()
     //           is MainNavItem.Placeholder1 -> MapScreen()
                is MainNavItem.FleetStatus -> FleetStatusScreen()
                is MainNavItem.Settings -> SettingsScreen()
                else -> Box(modifier = Modifier.fillMaxSize())
            }
        }
    }
}