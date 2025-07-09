package com.valsgroup.fleetx.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun StatusCard(
    icon: Painter,
    label: String,
    count: Int,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = icon,
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(text = label, color = Color.White, style = MaterialTheme.typography.bodyMedium)
        Text(text = count.toString(), color = Color.White, style = MaterialTheme.typography.titleLarge)
    }
} 