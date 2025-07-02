package com.valsgroup.fleetx.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valsgroup.fleetx.R
import com.valsgroup.fleetx.ui.theme.GrayMedium
import com.valsgroup.fleetx.ui.theme.White

@Composable
fun FleetStatusCard(
    totalObjects: Int,
    running: Int,
    idle: Int,
    stopped: Int,
    inactive: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(start = 0.dp)
        ,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            // Orange left border
            Box(
                modifier = Modifier
                    .height(440.dp)
                    .width(16.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp))
                    .background(Color(0xFF489FF8))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.padding(16.dp).weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.status),
                        contentDescription = "Status Icon",
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Fleet Status",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "See all",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                // Placeholder for circular chart
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Outer circle (Running)
                    Box(
                        modifier = Modifier
                            .size(170.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF4CAF50).copy(alpha = 0.15f))
                    )
                    // 2nd circle (Idle)
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFC107).copy(alpha = 0.15f))
                    )
                    // 3rd circle (Stopped)
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF44336).copy(alpha = 0.15f))
                    )
                    // 4th circle (Inactive)
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF2196F3).copy(alpha = 0.15f))
                    )
                    // Center text
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "$totalObjects",
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
                        Text(
                            text = "Objects",
                            style = MaterialTheme.typography.bodyMedium,
                            color = GrayMedium
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                // Status rows
                StatusRow(
                    color = Color(0xFF4CAF50),
                    label = "Running",
                    percent = "1%",
                    value = "$running Vehicles"
                )
                Spacer(modifier = Modifier.height(8.dp))
                StatusRow(
                    color = Color(0xFFFFC107),
                    label = "Idle",
                    percent = "5%",
                    value = "$idle Vehicles"
                )
                Spacer(modifier = Modifier.height(8.dp))
                StatusRow(
                    color = Color(0xFFF44336),
                    label = "Stopped",
                    percent = "39%",
                    value = "$stopped Vehicles"
                )
                Spacer(modifier = Modifier.height(8.dp))
                StatusRow(
                    color = Color(0xFF2196F3),
                    label = "In Active",
                    percent = "1%",
                    value = "$inactive Vehicle"
                )
            }
        }
    }
}

@Composable
private fun StatusRow(
    color: Color,
    label: String,
    percent: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(color),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = when (label) {
                "Running" -> painterResource(id = R.drawable.running)
                "Idle" -> painterResource(id = R.drawable.hourglass)
                "Stopped" -> painterResource(id = R.drawable.cross)
                else -> painterResource(id = R.drawable.stay_away)
            },
            contentDescription = "$label Icon",
            modifier = Modifier
                .padding(start = 8.dp)
                .size(28.dp)
                .clip(CircleShape)
                .background(White)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = White
            )
            Text(
                text = percent,
                style = MaterialTheme.typography.bodySmall,
                color = White
            )
        }
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = White,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FleetStatusCardPreview() {
    FleetStatusCard(
        totalObjects = 105,
        running = 60,
        idle = 5,
        stopped = 39,
        inactive = 1
    )
}