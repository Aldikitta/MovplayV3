package com.example.movplayv3.domain.usecase

import android.graphics.Bitmap
import com.example.movplayv3.utils.Roi
import com.example.movplayv3.utils.TextRecognitionHelper
import kotlinx.coroutines.flow.Flow

interface ScanBitmapForTextUseCaseImpl {
    operator fun invoke(
        bitmap: Bitmap,
        rotation: Float,
        roi: Roi?
    ): Flow<TextRecognitionHelper.ScanState>
}