package com.example.movplayv3.domain.usecase.interfaces

import android.graphics.Bitmap
import com.example.movplayv3.utils.Roi
import com.example.movplayv3.utils.TextRecognitionHelper
import kotlinx.coroutines.flow.Flow

interface ScanBitmapForTextUseCase {
    operator fun invoke(
        bitmap: Bitmap,
        rotation: Float,
        roi: Roi?
    ): Flow<TextRecognitionHelper.ScanState>
}