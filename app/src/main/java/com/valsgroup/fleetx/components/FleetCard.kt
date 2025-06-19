package com.valsgroup.fleetx.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valsgroup.fleetx.ui.theme.OrangePrimary
import com.valsgroup.fleetx.ui.theme.GrayMedium
import com.valsgroup.fleetx.ui.theme.GrayLight
import com.valsgroup.fleetx.ui.theme.White

@Composable
fun FleetCard(
    vehicleNumber: String,
    runningStatus: String,
    batteryPercent: String,
    voltage: String,
    address: String,
    gpsStatus: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = vehicleNumber,
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp),
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Placeholder for truck icon
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(GrayLight)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = runningStatus,
                            style = MaterialTheme.typography.bodyMedium,
                            color = GrayMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Placeholder for battery icon
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(GrayLight)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = batteryPercent,
                            style = MaterialTheme.typography.bodyMedium,
                            color = GrayMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Placeholder for voltage icon
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(GrayLight)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = voltage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = GrayMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Placeholder for location icon
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(OrangePrimary)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = address,
                            style = MaterialTheme.typography.bodyMedium,
                            color = GrayMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Placeholder for GPS icon
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(GrayLight)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = gpsStatus,
                            style = MaterialTheme.typography.bodyMedium,
                            color = GrayMedium
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                // Placeholder for truck image
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 90.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(GrayLight)
                )
            }
            // Action buttons (bottom right)
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(top = 120.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = { /* TODO: Power action */ }) {
                    // Placeholder for power icon
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF44336))
                    )
                }
                IconButton(onClick = { /* TODO: AC action */ }) {
                    // Placeholder for AC icon
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF2196F3))
                    )
                }
                IconButton(onClick = { /* TODO: Settings action */ }) {
                    // Placeholder for settings icon
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF4CAF50))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FleetCardPreview() {
    FleetCard(
        vehicleNumber = "JV-9060",
        runningStatus = "Running Since 2 Hours",
        batteryPercent = "0%",
        voltage = "12.525 V",
        address = "Ali Abad Stop, National Hwy 5, Shaheed Benazirabad, Sindh, P...",
        gpsStatus = "GPS Device Connected"
    )
} 