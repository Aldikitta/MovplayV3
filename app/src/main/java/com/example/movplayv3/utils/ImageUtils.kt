package com.example.movplayv3.utils

import android.content.Context
import android.graphics.Bitmap
import androidx.core.content.ContextCompat
import java.nio.ByteBuffer
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

data class Roi(
    val left: Float,
    val top: Float,
    val width: Float,
    val height: Float
)


fun Bitmap.getRoi(roi: Roi): Bitmap {
    return Bitmap.createBitmap(
        this,
        (roi.left * width).toInt(),
        (roi.top * height).toInt(),
        (roi.width * width).toInt(),
        (roi.height * height).toInt()
    )
}

fun ImageProxy.toBitmap(rotation: Float? = null): Bitmap {
    val buffer: ByteBuffer = planes[0].buffer
    val bytes = ByteArray(buffer.remaining()).also {
        buffer.get(it)
    }
    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

    return rotation?.let { deg -> bitmap.rotate(deg) } ?: bitmap
}

fun Bitmap.rotate(degrees: Float): Bitmap {
    return Bitmap.createBitmap(
        this,
        0,
        0,
        width,
        height,
        android.graphics.Matrix().apply { postRotate(degrees) },
        true
    )
}

suspend fun Context.getCameraProvider(): ProcessCameraProvider =
    suspendCoroutine { continuation ->
        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
            cameraProvider.addListener({
                continuation.run { resume(cameraProvider.get()) }
            }, ContextCompat.getMainExecutor(this))
        }
    }
