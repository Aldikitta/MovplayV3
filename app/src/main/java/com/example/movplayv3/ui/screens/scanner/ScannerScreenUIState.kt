package com.example.movplayv3.ui.screens.scanner

import androidx.compose.runtime.Stable

@Stable
data class ScannerScreenUIState(
    val scanningInProgress: Boolean,
    val scanResult: ScanResult?,
    val validationErrorResId: Int?
) {
    companion object {
        val default: ScannerScreenUIState = ScannerScreenUIState(
            scanningInProgress = false,
            scanResult = null,
            validationErrorResId = 0
        )
    }
}

@Stable
sealed class ScanResult {
    data class Success(val text: String) : ScanResult()
    data class Error(val message: String) : ScanResult()
}