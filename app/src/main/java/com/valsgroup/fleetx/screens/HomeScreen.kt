package com.valsgroup.fleetx.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valsgroup.fleetx.components.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile image placeholder
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E0E0))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Welcome!",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
                Text(
                    text = "Check your Fleet details",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            // Notification icon placeholder
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                // Replace with Icon if available
                Text("🔔", fontSize = 18.sp)
            }
        }
        // Dynamic banner (loading placeholder)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Loading...", color = Color.Gray, fontSize = 18.sp)
            }
        }
        // Mini cards row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FleetStatusMini(modifier = Modifier.weight(1f))
            FleetUsageMini(modifier = Modifier.weight(1f))
            FleetFuelMini(modifier = Modifier.weight(1f))
        }
        // Fleet Status
        FleetStatusCard(
            totalObjects = 105,
            running = 60,
            idle = 5,
            stopped = 39,
            inactive = 1
        )
        // Alerts
        AlertCard(
            label = "Stopped",
            sublabel = "102 Objects",
            value = "18467"
        )
        // Fleet Usage
        FleetUsageCard(
            totalUsage = "23049.08 Km",
            avgDistance = "219.52",
            usageData = listOf(30f, 60f, 45f, 80f, 55f, 40f, 70f, 65f, 90f, 50f, 60f, 75f)
        )
        // Fleet Idle
        FleetIdleCard(
            totalIdle = "65 Hours",
            fuelWaste = "124 Liters"
        )
        // AC Misuse, Stay In Zone (row)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AcMisuseCard(
                title = "AC Misuse",
                fuelWaste = "0 Litters",
                hours = "0",
                percent = "0%",
                modifier = Modifier
                    .weight(1f)
                    .height(180.dp)
            )
            StayInZoneCard(
                title = "Stay In Zone",
                fuelWaste = "0 Litters",
                hours = "0",
                percent = "57%",
                modifier = Modifier
                    .weight(1f)
                    .height(180.dp)
            )
        }
        // Stay Away Zone, Temperature (row)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StayAwayZoneCard(
                title = "Stay Away From Zone",
                alerts = "0",
                percent = "0%",
                modifier = Modifier
                    .weight(1f)
                    .height(180.dp)
            )
            TemperatureCard(
                title = "Temperature",
                min = "25.0 C",
                max = "84.8 C",
                percent = "0%",
                modifier = Modifier
                    .weight(1f)
                    .height(180.dp)
            )
        }
        // Object Mode
        ObjectModeCard(
            modes = listOf(
                ObjectMode("Good To Go", 0.95f, "95"),
                ObjectMode("On Job", 0.06f, "06"),
                ObjectMode("Repair", 0.01f, "01"),
                ObjectMode("Accident", 0.01f, "01"),
                ObjectMode("Breakdown", 0.01f, "01"),
                ObjectMode("Private Mode", 0.01f, "01"),
                ObjectMode("Occupied", 0.01f, "01")
            )
        )
        // Object Type
        ObjectTypeCard(
            types = listOf(
                ObjectType("CPCD 50-W", 0.95f, "95"),
                ObjectType("Hino 500", 0.06f, "06"),
                ObjectType("4WD Truck-SML Isuzu", 0.01f, "01"),
                ObjectType("Isuzu", 0.01f, "01"),
                ObjectType("General", 0.01f, "01")
            )
        )
        // Work Efficiency
        WorkEfficiencyCard(
            label = "Work Efficiency",
            value = "12 hrs",
            progress = 0.85f
        )
    }
} 