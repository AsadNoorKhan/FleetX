package com.valsgroup.fleetx.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    placeholder: String = "Search location\nStreet · Block · Town",
    onValueChange: (String) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    OutlinedTextField(
        value = searchText,
        onValueChange = {
            searchText = it
            onValueChange(it)
        },
        leadingIcon = { androidx.compose.material3.Icon(Icons.Default.Search, contentDescription = "Search") },
        placeholder = { Text(placeholder) },
        shape = RoundedCornerShape(24.dp),
        modifier = modifier,
        singleLine = true,
        maxLines = 1
    )
} 