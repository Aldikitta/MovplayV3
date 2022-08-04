package com.example.movplayv3.ui.components.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun MovplayAdditionalInfoText(
    infoTexts: List<String>,
    modifier: Modifier = Modifier
) {
    val text = infoTexts.joinToString(separator = " · ")

    Text(
        modifier = modifier,
        text = text,
//        color = Color.White.copy(0.5f),
//        fontSize = 12.sp
    )
}