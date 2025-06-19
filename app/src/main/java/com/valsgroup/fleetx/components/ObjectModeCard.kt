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
import com.valsgroup.fleetx.ui.theme.GrayMedium

@Composable
fun ObjectModeCard(
    modes: List<ObjectMode>,
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
                // Placeholder for mode icon
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF2196F3))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Object Mode",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "See all",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = OrangePrimary
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            modes.forEach { mode ->
                ObjectModeRow(mode)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun ObjectModeRow(mode: ObjectMode) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Placeholder for mode icon
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE3F0FF))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = mode.label,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color(0xFFE3F0FF))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(mode.progress)
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color(0xFF2196F3))
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = mode.count,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
                textAlign = TextAlign.End
            )
        }
    }
}

data class ObjectMode(
    val label: String,
    val progress: Float, // 0.0 to 1.0
    val count: String
)

@Preview(showBackground = true)
@Composable
fun ObjectModeCardPreview() {
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
} 