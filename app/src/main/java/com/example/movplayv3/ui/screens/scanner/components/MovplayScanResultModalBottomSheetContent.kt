package com.example.movplayv3.ui.screens.scanner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movplayv3.R
import com.example.movplayv3.ui.screens.scanner.ScanResult
import com.example.movplayv3.ui.theme.spacing

@Composable
fun MovplayScanResultModalBottomSheetContent(
    scanResult: ScanResult?,
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit = {},
    onRejectClicked: () -> Unit = {},
    onAcceptClicked: () -> Unit = {}
) {
    val scannedText = if (scanResult is ScanResult.Success) scanResult.text else ""
    
    Column(
        modifier = modifier.padding(top = MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(
            modifier = Modifier.width(64.dp),
            color = Color.White.copy(0.3f),
            thickness = 4.dp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = onCloseClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "close filter",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Text(text = scannedText, fontSize = 32.sp, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(vertical = MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium),
                onClick = onAcceptClicked
            ) {
                Text(text = stringResource(R.string.scanner_accept_button_label))
            }
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium),
                onClick = onRejectClicked
            ) {
                Text(text = stringResource(R.string.scanner_reject_button_label))
            }
        }
    }
}