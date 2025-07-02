package com.valsgroup.fleetx.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valsgroup.fleetx.components.FleetCard

// Data class for vehicle info (to be replaced with API data)
data class VehicleInfo(
    val vehicleNumber: String,
    val runningStatus: String,
    val batteryPercent: String,
    val voltage: String,
    val address: String,
    val gpsStatus: String
)

@Composable
fun FleetStatusScreen(
    vehicles: List<VehicleInfo> = sampleVehicles // Replace with API data in the future
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        // Top header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Menu icon placeholder
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Text("≡", fontSize = 28.sp, color = MaterialTheme.colorScheme.onSurface)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Status Indus Dashboard",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            // Notification icon placeholder
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🔔",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        // List of FleetCards
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 80.dp) // for bottom nav
        ) {
            items(vehicles) { vehicle ->
                FleetCard(
                    vehicleNumber = vehicle.vehicleNumber,
                    runningStatus = vehicle.runningStatus,
                    batteryPercent = vehicle.batteryPercent,
                    voltage = vehicle.voltage,
                    address = vehicle.address,
                    gpsStatus = vehicle.gpsStatus,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

// Sample data for preview/testing
val sampleVehicles = listOf(
    VehicleInfo(
        vehicleNumber = "JV-9060",
        runningStatus = "Running Since 2 Hours",
        batteryPercent = "0%",
        voltage = "12.525 V",
        address = "Ali Abad Stop, National Hwy 5, Shaheed Benazirabad, Sindh, P...",
        gpsStatus = "GPS Device Connected"
    ),
    VehicleInfo(
        vehicleNumber = "JV-9060",
        runningStatus = "Running Since 2 Hours",
        batteryPercent = "0%",
        voltage = "12.525 V",
        address = "Ali Abad Stop, National Hwy 5, Shaheed Benazirabad, Sindh, P...",
        gpsStatus = "GPS Device Not Connected"
    ),
    VehicleInfo(
        vehicleNumber = "JV-9060",
        runningStatus = "Running Since 2 Hours",
        batteryPercent = "0%",
        voltage = "12.525 V",
        address = "Ali Abad Stop, National Hwy 5, Shaheed Benazirabad, Sindh, P...",
        gpsStatus = "GPS Device Connected"
    )
)

@Composable
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
fun FleetStatusScreenPreview() {
    FleetStatusScreen()
} 