package com.example.movplayv3.ui.screens.scanner.components

import android.graphics.Bitmap
import android.graphics.Paint
import android.os.Build
import androidx.camera.core.AspectRatio
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.movplayv3.R
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.Roi
import com.example.movplayv3.utils.toBitmap

@Composable
fun MovplayTitleScanner(
    modifier: Modifier = Modifier,
    isScanningInProgress: Boolean = false,
    errorText: String? = null,
    onBitmapCaptured: (image: Bitmap, rotation: Float, roi: Roi?) -> Unit,
) {
    val backgroundColorCameraPreview = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)

    val context = LocalContext.current
    val imageCapture = remember {
        ImageCapture.Builder()
            .setTargetAspectRatio(AspectRatio.RATIO_4_3)
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .build()
    }
    val executor = remember(context) {
        ContextCompat.getMainExecutor(context)
    }
    val borderColor = MaterialTheme.colorScheme.primary
    val errorTextSize = with(LocalDensity.current) {
        16.sp.toPx()
    }
    val errorTextPainter = remember {
        Paint().apply {
            color = Color.Red.toArgb()
            textSize = errorTextSize
            typeface = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.resources.getFont(R.font.lato_black)
            } else ResourcesCompat.getFont(context, R.font.lato_black)
            textAlign = Paint.Align.CENTER
        }
    }
    var roi: Roi? = remember { null }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(modifier = modifier.aspectRatio(3f / 4f)) {
            MovplayCameraPreviewView(
                modifier = Modifier.fillMaxSize(),
                imageCapture = imageCapture
            )

            Canvas(modifier = Modifier.fillMaxSize()) {
                val rectHorizontalPadding = 32.dp.toPx()
                val rectWidth = size.width - 2 * rectHorizontalPadding
                val rectHeight = 48.dp.toPx()
                val cornerRadius = CornerRadius(
                    x = 16.dp.toPx(),
                    y = 16.dp.toPx()
                )

                val rect = Rect(
                    offset = Offset(
                        rectHorizontalPadding,
                        size.height * 0.33f - rectHeight / 2
                    ),
                    size = Size(rectWidth, rectHeight)
                )

                roi = Roi(
                    left = rect.left / size.width,
                    top = rect.top / size.height,
                    width = rect.width / size.width,
                    height = rect.height / size.height
                )
                val path = Path().apply {
                    val roundRect = RoundRect(
                        rect = rect,
                        cornerRadius = cornerRadius
                    )

                    addRoundRect(roundRect)
                }

                clipPath(path, clipOp = ClipOp.Difference) {
                    drawRect(SolidColor(backgroundColorCameraPreview))
                }

                drawRoundRect(
                    topLeft = rect.topLeft,
                    size = rect.size,
                    cornerRadius = cornerRadius,
                    color = borderColor,
                    style = Stroke(width = 2.dp.toPx())
                )

                errorText?.let { text ->
                    drawIntoCanvas {
                        with(it.nativeCanvas) {
                            drawText(
                                text,
                                rect.center.x,
                                rect.bottom + 16.dp.toPx() + errorTextSize / 2,
                                errorTextPainter
                            )
                        }
                    }
                }
            }
        }

        MovplayScannerAcceptButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(MaterialTheme.spacing.large)
                .size(80.dp),
            isScanningInProgress = isScanningInProgress,
            onClick = {
                imageCapture.takePicture(
                    executor,
                    object : ImageCapture.OnImageCapturedCallback() {
                        override fun onCaptureSuccess(image: ImageProxy) {
                            image.use { proxy ->
                                try {
                                    val rotation = image.imageInfo.rotationDegrees.toFloat()
                                    val bitmap = proxy.toBitmap()

                                    onBitmapCaptured(bitmap, rotation, roi)
                                } catch (_: IllegalArgumentException) {

                                }
                            }
                        }
                    }
                )
            }
        )
    }
}