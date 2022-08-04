package com.example.movplayv3.ui.components.texts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.movplayv3.ui.theme.spacing

@Composable
fun MovplayLabeledText(
    label: String,
    text: String,
    modifier: Modifier = Modifier,
    spacing: Dp = MaterialTheme.spacing.default
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        Text(
            text = label,
//            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = text,
//            fontSize = 12.sp
        )
    }
}