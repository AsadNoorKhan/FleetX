package com.valsgroup.fleetx.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.launch
import com.valsgroup.fleetx.R

@Composable
fun HomeScreen() {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile image
                Image(
                    painter = painterResource(id = R.drawable.pp),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
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
                // Notification icon (bell emoji)
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🔔",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        
        // Banner image
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.banner),
                    contentDescription = "Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
        
        // Mini cards row
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FleetStatusMini(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        coroutineScope.launch {
                            // Scroll to Fleet Status Card (index 3 after header, banner, and mini cards)
                            listState.animateScrollToItem(3)
                        }
                    }
                )
                FleetUsageMini(
                    modifier = Modifier.weight(1f),
                    painter = painterResource(id = R.drawable.usage_pie),
                    onClick = {
                        coroutineScope.launch {
                            // Scroll to Fleet Usage Card (index 5 after header, banner, mini cards, fleet status, and alerts)
                            listState.animateScrollToItem(5)
                        }
                    }
                )
                FleetFuelMini(
                    modifier = Modifier.weight(1f),
                    painter = painterResource(id = R.drawable.fuel),
                    onClick = {
                        coroutineScope.launch {
                            // Scroll to Fleet Fuel Card (index 9 after header, banner, mini cards, fleet status, alerts, usage, idle, ac misuse/stay in zone, stay away/temperature)
                            listState.animateScrollToItem(9)
                        }
                    }
                )
            }
        }
        
        // Fleet Status
        item {
            FleetStatusCard(
                totalObjects = 105,
                running = 60,
                idle = 5,
                stopped = 39,
                inactive = 1
            )
        }
        
        // Alerts
        item {
            AlertCard(
                label = "Stopped",
                sublabel = "102 Objects",
                value = "18467",
                painter = painterResource(id = R.drawable.alert)
            )
        }
        
        // Fleet Usage
        item {
            FleetUsageCard(
                totalUsage = "23049.08 Km",
                avgDistance = "219.52",
                usageData = listOf(30f, 60f, 45f, 80f, 55f, 40f, 70f, 65f, 90f, 50f, 60f, 75f),
                painter = painterResource(id = R.drawable.usage_pie)
            )
        }
        
        // Fleet Idle
        item {
            FleetIdleCard(
                totalIdle = "65 Hours",
                fuelWaste = "124 Liters",
                painter = painterResource(id = R.drawable.hourglass),
                idleIconPainter = painterResource(id = R.drawable.hourglass),
                fuelWasteIconPainter = painterResource(id = R.drawable.empty_gas)
            )
        }
        
        // AC Misuse, Stay In Zone (row)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AcMisuseCard(
                    title = "AC Misuse",
                    fuelWaste = "0 Litters",
                    hours = "0",
                    percent = "0%",
                    painter = painterResource(id = R.drawable.ac_misuse),
                    modifier = Modifier
                        .weight(1f)
                        .height(210.dp)
                )
                StayInZoneCard(
                    title = "Stay In Zone",
                    fuelWaste = "0 Litters",
                    hours = "0",
                    percent = "57%",
                    painter = painterResource(id = R.drawable.stay_in_zone),
                    modifier = Modifier
                        .weight(1f)
                        .height(210.dp)
                )
            }
        }
        
        // Stay Away Zone, Temperature (row)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StayAwayZoneCard(
                    title = "Stay Away From Zone",
                    alerts = "0",
                    percent = "0%",
                    painter = painterResource(id = R.drawable.stay_away_zone),
                    modifier = Modifier
                        .weight(1f)
                        .height(210.dp)
                )
                TemperatureCard(
                    title = "Temperature",
                    min = "25.0 C",
                    max = "84.8 C",
                    percent = "0%",
                    painter = painterResource(id = R.drawable.temp),
                    modifier = Modifier
                        .weight(1f)
                        .height(210.dp)
                )
            }
        }
        
        // Fleet Fuel Card
        item {
            FleetFuelCard(
                totalRefill = "0 Liters",
                refillTimes = "(0 Times)",
                totalDrain = "0 Liters",
                drainTimes = "(0 Times)",
                topLeftPainter = painterResource(id = R.drawable.fuel),
                refillPainter = painterResource(id = R.drawable.fuel),
                drainPainter = painterResource(id = R.drawable.fuel_drain)
            )
        }
        
        // Object Mode
        item {
            ObjectModeCard(
                modes = listOf(
                    ObjectMode("Good To Go", 0.95f, "95", painterResource(id = R.drawable.gtg)),
                    ObjectMode("On Job", 0.06f, "06", painterResource(id = R.drawable.on_job)),
                    ObjectMode("Accident", 0.01f, "01", painterResource(id = R.drawable.accident)),
                    ObjectMode("Breakdown", 0.01f, "01", painterResource(id = R.drawable.breakdown)),
                    ObjectMode("Private Mode", 0.01f, "01", painterResource(id = R.drawable.pvt)),
                    ObjectMode("Occupied", 0.01f, "01", painterResource(id = R.drawable.occupied))
                )
            )
        }
        
        // Object Type
        item {
            ObjectTypeCard(
                types = listOf(
                    ObjectType("CPCD 50-W", 0.95f, "95", painterResource(id = R.drawable.cpcd_50_w)),
                    ObjectType("Hino 500", 0.06f, "06", painterResource(id = R.drawable.hino_500)),
                    ObjectType("4WD Truck-SML Isuzu", 0.01f, "01", painterResource(id = R.drawable.isuzu_4wd_truck_sml)),
                    ObjectType("Isuzu", 0.01f, "01", painterResource(id = R.drawable.isuzu)),
                    ObjectType("General", 0.01f, "01", painterResource(id = R.drawable.general_truck))
                )
            )
        }
        
        // Work Efficiency
        item {
            WorkEfficiencyCard(
                label = "Work Efficiency",
                value = "12 hrs",
                progress = 0.85f,
                painter = painterResource(id = R.drawable.work_effeciency)
            )
        }
    }
}