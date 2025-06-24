package com.valsgroup.fleetx.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valsgroup.fleetx.R

@Composable
fun FleetFuelCard(
    totalRefill: String,
    refillTimes: String,
    totalDrain: String,
    drainTimes: String,
    modifier: Modifier = Modifier,
    topLeftPainter: androidx.compose.ui.graphics.painter.Painter,
    refillPainter: androidx.compose.ui.graphics.painter.Painter,
    drainPainter: androidx.compose.ui.graphics.painter.Painter
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = topLeftPainter,
                    contentDescription = "Fleet Fuel Icon",
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Fleet Fuel",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF5F5F5))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Today",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFFFFA000)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        // Calendar icon placeholder (can be replaced with actual icon)
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFF2196F3))
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            FuelRow(
                icon = refillPainter,
                label = "Total Fuel Refill",
                value = totalRefill,
                times = refillTimes,
                color = Color(0xFFFFA000)
            )
            Spacer(modifier = Modifier.height(12.dp))
            FuelRow(
                icon = drainPainter,
                label = "Total Fuel Drain",
                value = totalDrain,
                times = drainTimes,
                color = Color(0xFF2196F3)
            )
        }
    }
}

@Composable
private fun FuelRow(
    icon: androidx.compose.ui.graphics.painter.Painter,
    label: String,
    value: String,
    times: String,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .background(color),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = icon,
            contentDescription = label,
            modifier = Modifier
                .padding(start = 8.dp)
                .size(28.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
            Text(
                text = times,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
} 