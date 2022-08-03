package com.example.movplayv3.ui.screens.scanner

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.example.movplayv3.BaseViewModel
import com.example.movplayv3.domain.usecase.interfaces.ScanBitmapForTextUseCase
import com.example.movplayv3.utils.Roi
import com.example.movplayv3.utils.TextRecognitionHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScannerScreenViewModel @Inject constructor(
    private val scanBitmapForTextUseCase: ScanBitmapForTextUseCase
) : BaseViewModel() {
    private val scanState: MutableStateFlow<TextRecognitionHelper.ScanState> = MutableStateFlow(
        TextRecognitionHelper.ScanState.Idle
    )

    val uiState: StateFlow<ScannerScreenUIState> = scanState.map { scanState ->
        val isScanInProgress = scanState is TextRecognitionHelper.ScanState.Loading
        val scanResult = when (scanState) {
            is TextRecognitionHelper.ScanState.Success -> ScanResult.Success(scanState.text)
            is TextRecognitionHelper.ScanState.Error -> ScanResult.Error(scanState.message)
            else -> null
        }
        val validationErrorResId = when (scanState) {
            is TextRecognitionHelper.ScanState.InvalidText -> scanState.errorResId
            else -> null
        }
        ScannerScreenUIState(
            scanningInProgress = isScanInProgress,
            scanResult = scanResult,
            validationErrorResId = validationErrorResId
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, ScannerScreenUIState.default)

    fun onBitmapCaptured(bitmap: Bitmap, rotation: Float, roi: Roi?) {
        viewModelScope.launch(Dispatchers.IO) {
            scanBitmapForTextUseCase(bitmap, rotation, roi).collect { state ->
                scanState.emit(state)
            }
        }
    }
}