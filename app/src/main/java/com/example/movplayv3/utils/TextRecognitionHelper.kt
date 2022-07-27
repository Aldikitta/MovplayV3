package com.example.movplayv3.utils

import android.graphics.Bitmap
import com.example.movplayv3.R
import com.google.android.gms.tasks.OnCanceledListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


abstract class TextRecognitionHelper {
    abstract fun scanTextFromBitmap(bitmap: Bitmap, rotation: Float, roi: Roi?): Flow<ScanState>

    sealed class ScanState {
        object Idle : ScanState()
        object Loading : ScanState()
        data class InvalidText(val errorResId: Int) : ScanState()
        data class Success(val text: String) : ScanState()
        data class Error(val message: String) : ScanState()
    }
}

class TextRecognitionHelperImpl @Inject constructor() : TextRecognitionHelper() {
    private val textRecognition = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    override fun scanTextFromBitmap(
        bitmap: Bitmap,
        rotation: Float,
        roi: Roi?
    ): Flow<ScanState> = callbackFlow {
        trySend(ScanState.Loading)

        val processedBitmap = preprocessBitmap(bitmap, rotation, roi)
        val onCompleteTaskListener = OnCompleteListener<Text> {
            if (it.isSuccessful) {
                val text = it.result?.text?.let { text ->
                    preprocessText(text)
                } ?: ""

                when (val validationResult = ScannedTextValidator.validateText(text)) {
                    is ScannedTextValidator.ValidationResult.Valid -> {
                        trySend(ScanState.Success(text))
                    }

                    is ScannedTextValidator.ValidationResult.Invalid -> {
                        trySend(ScanState.InvalidText(validationResult.errorResId))
                    }
                }
            }
        }

        val onFailureTaskListener = OnFailureListener {
            trySend(ScanState.Error(it.message.toString()))
        }

        val onCancelTaskListener = OnCanceledListener {
            trySend(ScanState.Idle)
        }

        textRecognition.process(processedBitmap, 0).apply {
            addOnCompleteListener(onCompleteTaskListener)
            addOnFailureListener(onFailureTaskListener)
            addOnCanceledListener(onCancelTaskListener)
        }

        awaitClose {

        }
    }

    private fun preprocessBitmap(
        bitmap: Bitmap,
        rotation: Float,
        roi: Roi?
    ): Bitmap {
        val rotatedBitmap = bitmap.rotate(rotation)
        return roi?.let { rotatedBitmap.getRoi(it) } ?: rotatedBitmap
    }

    private fun preprocessText(text: String): String {
        return text.replace("\n", " ")
    }
}

object ScannedTextValidator {
    private const val MIN_TEXT_LENGTH = 3
    private const val MAX_TEST_LENGTH = 20

    sealed class ValidationResult {
        object Valid : ValidationResult()
        sealed class Invalid(val errorResId: Int) : ValidationResult() {
            object Empty : Invalid(R.string.scanner_error_info_no_text)
            object ToShort : Invalid(R.string.scanner_error_info_to_short_text)
            object ToLong : Invalid(R.string.scanner_error_info_to_long_text)
        }
    }

    fun validateText(text: String) = when {
        text.isBlank() -> ValidationResult.Invalid.Empty
        text.length < MIN_TEXT_LENGTH -> ValidationResult.Invalid.ToShort
        text.length > MAX_TEST_LENGTH -> ValidationResult.Invalid.ToLong
        else -> ValidationResult.Valid
    }
}