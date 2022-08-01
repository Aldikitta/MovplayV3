package com.example.movplayv3.ui.components.dialogs

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.movplayv3.R

@Composable
fun MovplayExitDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onConfirmClick: () -> Unit = {},
    onCancelClick: () -> Unit = {},
) {
    MovplayApplicationDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        infoText = stringResource(R.string.exit_dialog_info),
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmClick()
                }
            ) {
                Text(
                    stringResource(R.string.exit_dialog_confirm_button_label)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onCancelClick()
                }
            ) {
                Text(
                    stringResource(R.string.exit_dialog_cancel_button_label)
                )
            }
        },
    )
}