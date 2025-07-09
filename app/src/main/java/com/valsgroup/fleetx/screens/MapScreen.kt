package com.valsgroup.fleetx.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.valsgroup.fleetx.components.SearchBar
import com.valsgroup.fleetx.components.StatusCard

@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    val karachi = LatLng(24.8607, 67.0011)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(karachi, 12f)
    }
    
    val context = LocalContext.current

    val mapLoaded = remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        // Google Map
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(mapType = MapType.NORMAL),
            onMapLoaded = { mapLoaded.value = true }
        ) {
            if (mapLoaded.value) {
                val markerIcon = BitmapDescriptorFactory.fromResource(com.valsgroup.fleetx.R.drawable.map_marker)
                val markerPositions = listOf(
                    LatLng(24.8607, 67.0011),
                    LatLng(24.8650, 67.0200),
                    LatLng(24.8700, 67.0300),
                    LatLng(24.8500, 67.0100)
                )
                markerPositions.forEachIndexed { idx, pos ->
                    Marker(
                        state = MarkerState(position = pos),
                        title = "Marker ${idx + 1}",
                        icon = markerIcon
                    )
                }
            }
        }

        // Overlay: Status Cards and Search Bar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 80.dp) // leave space for bottom nav
                .align(BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterHorizontally)
            ) {
                StatusCard(
                    icon = painterResource(id = com.valsgroup.fleetx.R.drawable.map_all),
                    label = "All",
                    count = 106,
                    backgroundColor = Color(0xFFFF7043)
                )
                StatusCard(
                    icon = painterResource(id = com.valsgroup.fleetx.R.drawable.map_active),
                    label = "Active",
                    count = 64,
                    backgroundColor = Color(0xFF43A047)
                )
                StatusCard(
                    icon = painterResource(id = com.valsgroup.fleetx.R.drawable.stay_away),
                    label = "Stop",
                    count = 36,
                    backgroundColor = Color(0xFFD32F2F)
                )
                StatusCard(
                    icon = painterResource(id = com.valsgroup.fleetx.R.drawable.map_idle),
                    label = "Idle",
                    count = 12,
                    backgroundColor = Color(0xFFFFA726)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}