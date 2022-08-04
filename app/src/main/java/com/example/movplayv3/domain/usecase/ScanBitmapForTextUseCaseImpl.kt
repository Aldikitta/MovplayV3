package com.example.movplayv3.domain.usecase

import android.graphics.Bitmap
import com.example.movplayv3.utils.Roi
import com.example.movplayv3.utils.TextRecognitionHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScanBitmapForTextUseCaseImpl @Inject constructor(
    private val textRecognitionHelper: TextRecognitionHelper
) {
    operator fun invoke(
        bitmap: Bitmap,
        rotation: Float,
        roi: Roi?
    ): Flow<TextRecognitionHelper.ScanState> {
        return textRecognitionHelper.scanTextFromBitmap(bitmap, rotation, roi)
    }
}