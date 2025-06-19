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
import com.valsgroup.fleetx.ui.theme.GrayMedium
import com.valsgroup.fleetx.ui.theme.White

@Composable
fun FleetUsageCard(
    totalUsage: String,
    avgDistance: String,
    usageData: List<Float>,
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
                // Placeholder for pie chart icon
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF4CAF50))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Fleet Usage",
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
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Total Fleet Usage",
                    style = MaterialTheme.typography.bodySmall,
                    color = GrayMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Avg. Distance / Object",
                    style = MaterialTheme.typography.bodySmall,
                    color = GrayMedium,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                Text(
                    text = totalUsage,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = avgDistance,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Placeholder for bar chart
            BarChartPlaceholder(usageData)
        }
    }
}

@Composable
private fun BarChartPlaceholder(data: List<Float>) {
    val max = (data.maxOrNull() ?: 1f)
    val months = listOf("Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        data.take(12).forEachIndexed { i, value ->
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .width(14.dp)
                        .height(80.dp * (value / max))
                        .clip(RoundedCornerShape(7.dp))
                        .background(Color(0xFF4CAF50))
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = months.getOrElse(i) { "" },
                    style = MaterialTheme.typography.bodySmall,
                    color = GrayMedium,
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FleetUsageCardPreview() {
    FleetUsageCard(
        totalUsage = "23049.08 Km",
        avgDistance = "219.52",
        usageData = listOf(30f, 60f, 45f, 80f, 55f, 40f, 70f, 65f, 90f, 50f, 60f, 75f)
    )
} 