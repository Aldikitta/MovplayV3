package com.example.movplayv3.ui.components.dialogs

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.movplayv3.R

@Composable
fun MovplayErrorDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    errorMessage: String? = null,
    onConfirmClick: () -> Unit = {}
) {
    MovplayApplicationDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        infoText = errorMessage ?: stringResource(R.string.error_dialog_default_text),
        confirmButton = {
            OutlinedButton(
                onClick = onConfirmClick
            ) {
                Text(text = stringResource(R.string.exit_dialog_confirm_button_label))
            }
        }
    )
}