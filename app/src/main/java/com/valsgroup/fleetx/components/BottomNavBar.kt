package com.valsgroup.fleetx.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valsgroup.fleetx.ui.theme.OrangePrimary
import com.valsgroup.fleetx.ui.theme.GrayLight
import com.valsgroup.fleetx.ui.theme.GrayMedium
import com.valsgroup.fleetx.ui.theme.White
import com.valsgroup.fleetx.R

sealed class BottomNavItem(val label: String, val iconRes: Int) {
    object Home : BottomNavItem("Home", R.drawable.ic_launcher_foreground) // Placeholder icon
    object Dashboard : BottomNavItem("Dashboard", R.drawable.ic_launcher_foreground) // Placeholder icon
    object Notifications : BottomNavItem("Notifications", R.drawable.ic_notifications)
    object Profile : BottomNavItem("Profile", R.drawable.ic_person)
}

@Composable
fun BottomNavBar(
    selected: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit
) {
    NavigationBar(
        containerColor = White,
        tonalElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Dashboard,
            BottomNavItem.Notifications,
            BottomNavItem.Profile
        )
        items.forEach { item ->
            NavigationBarItem(
                selected = selected == item,
                onClick = { onItemSelected(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.label,
                        tint = if (selected == item) OrangePrimary else GrayMedium
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        color = if (selected == item) OrangePrimary else GrayMedium
                    )
                },
                alwaysShowLabel = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    var selected by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }
    BottomNavBar(selected = selected, onItemSelected = { selected = it })
} 