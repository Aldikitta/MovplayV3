package com.example.movplayv3.domain.usecase

import android.graphics.Bitmap
import com.example.movplayv3.domain.usecase.interfaces.ScanBitmapForTextUseCase
import com.example.movplayv3.utils.Roi
import com.example.movplayv3.utils.TextRecognitionHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScanBitmapForTextUseCaseImpl @Inject constructor() : ScanBitmapForTextUseCase {
    override fun invoke(
        bitmap: Bitmap,
        rotation: Float,
        roi: Roi?
    ): Flow<TextRecognitionHelper.ScanState> {
        TODO("Not yet implemented")
    }

}