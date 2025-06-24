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
fun ObjectTypeCard(
    types: List<ObjectType>,
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
                // Use the first type's painter as the main icon
                if (types.isNotEmpty()) {
                    Image(
                        painter = types[0].painter,
                        contentDescription = "Object Type Icon",
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Object Type",
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
            types.forEach { type ->
                ObjectTypeRow(type)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun ObjectTypeRow(type: ObjectType) {
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
            Image(
                painter = type.painter,
                contentDescription = "Object Type Row Icon",
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = type.label,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color(0xFFEDE7F6))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(type.progress)
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color(0xFFB39DDB))
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = type.count,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
                textAlign = TextAlign.End
            )
        }
    }
}

data class ObjectType(
    val label: String,
    val progress: Float, // 0.0 to 1.0
    val count: String,
    val painter: Painter
)

@Preview(showBackground = true)
@Composable
fun ObjectTypeCardPreview() {
    val painter = androidx.compose.ui.res.painterResource(android.R.drawable.ic_menu_info_details)
    ObjectTypeCard(
        types = listOf(
            ObjectType("CPCD 50-W", 0.95f, "95", painter),
            ObjectType("Hino 500", 0.06f, "06", painter),
            ObjectType("4WD Truck-SML Isuzu", 0.01f, "01", painter),
            ObjectType("Isuzu", 0.01f, "01", painter),
            ObjectType("General", 0.01f, "01", painter)
        )
    )
} 