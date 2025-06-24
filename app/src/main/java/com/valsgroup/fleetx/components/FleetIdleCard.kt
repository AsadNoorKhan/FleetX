package com.valsgroup.fleetx.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valsgroup.fleetx.ui.theme.OrangePrimary
import com.valsgroup.fleetx.ui.theme.White
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun FleetIdleCard(
    totalIdle: String,
    fuelWaste: String,
    painter: Painter,
    idleIconPainter: Painter,
    fuelWasteIconPainter: Painter,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painter,
                    contentDescription = "Idle Icon",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Fleet Idle",
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
                            color = OrangePrimary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        // Placeholder for calendar icon
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFF2196F3))
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            IdleRow(
                color = Color(0xFFFFC107),
                iconPainter = idleIconPainter,
                label = "Total Fleet Idle",
                value = totalIdle
            )
            Spacer(modifier = Modifier.height(12.dp))
            IdleRow(
                color = Color(0xFFF44336),
                iconPainter = fuelWasteIconPainter,
                label = "Approx Fuel Waste",
                value = fuelWaste
            )
        }
    }
}

@Composable
private fun IdleRow(
    color: Color,
    iconPainter: Painter,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(color),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = 8.dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = iconPainter,
                contentDescription = label,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = White,
            modifier = Modifier.weight(1f)
        )
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
fun FleetIdleCardPreview() {
    FleetIdleCard(
        totalIdle = "65 Hours",
        fuelWaste = "124 Liters",
        painter = androidx.compose.ui.res.painterResource(android.R.drawable.ic_menu_info_details),
        idleIconPainter = androidx.compose.ui.res.painterResource(android.R.drawable.ic_menu_recent_history),
        fuelWasteIconPainter = androidx.compose.ui.res.painterResource(android.R.drawable.ic_delete)
    )
} 