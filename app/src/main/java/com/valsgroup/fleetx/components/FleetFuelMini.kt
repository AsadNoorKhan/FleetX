package com.valsgroup.fleetx.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun FleetFuelMini(modifier: Modifier = Modifier, painter: Painter) {
    Card(
        modifier = modifier
            .width(120.dp)
            .height(80.dp)
            .border(2.dp, Color(0xFFFFA000), RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painter,
                contentDescription = "Fleet Fuel Icon",
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Fleet Fuel",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                color = Color.Black
            )
        }
    }
}

@Composable
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
fun FleetFuelMiniPreview() {
    // Use a placeholder painter for preview
    FleetFuelMini(painter = androidx.compose.ui.res.painterResource(android.R.drawable.ic_menu_info_details))
} 