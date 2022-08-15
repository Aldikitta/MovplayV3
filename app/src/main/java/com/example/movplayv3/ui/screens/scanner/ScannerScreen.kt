package com.example.movplayv3.ui.screens.scanner

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movplayv3.utils.Roi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalLifecycleComposeApi::class)
@Destination(
    style = ScannerScreenTransitions::class
)
@Composable
fun AnimatedVisibilityScope.ScannerScreen(
    viewModel: ScannerScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
    resultNavigator: ResultBackNavigator<String>
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val onBackClicked: () -> Unit = {
        navigator.navigateUp()
    }
    val onAcceptClicked = {
        val result = uiState.scanResult
        if (result is ScanResult.Success) {
            resultNavigator.navigateBack(result.text)
        }
    }

    ScannerScreenContent(
        uiState = uiState,
        onBackClicked = onBackClicked,
        onBitmapCaptured = viewModel::onBitmapCaptured,
        onAcceptClicked = onAcceptClicked
    )
}

@OptIn(
    ExperimentalPermissionsApi::class,
//    androidx.compose.material.ExperimentalMaterialApi::class
)
@Composable
fun ScannerScreenContent(
    uiState: ScannerScreenUIState,
    onBackClicked: () -> Unit = {},
    onBitmapCaptured: (Bitmap, Float, Roi?) -> Unit,
    onAcceptClicked: () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()

    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )
//    val sheetState = androidx.compose.material.rememberModalBottomSheetState(
//        initialValue = androidx.compose.material.ModalBottomSheetValue.Hidden,
//        skipHalfExpanded = true
//    )

//    val onCloseBottomSheetClick: () -> Unit = {
//        if (sheetState.isVisible) {
//            coroutineScope.launch {
//                sheetState.hide()
//            }
//        }
//    }

    val errorText = uiState.validationErrorResId?.let {
        stringResource(it)
    }

//    LaunchedEffect(uiState.scanResult) {
//        when (uiState.scanResult) {
//            is ScanResult.Success -> {
//                if (!sheetState.isVisible) {
//                    sheetState.show()
//                }
//            }
//            else -> {
//                if (sheetState.isVisible) {
//                    sheetState.hide()
//                }
//            }
//        }
//    }

//    androidx.compose.material.ModalBottomSheetLayout(
//        modifier = Modifier.fillMaxSize(),
//        sheetState = sheetState,
//        scrimColor = Color.Green,
//        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
//        sheetContent = {
//            MovplayScanResultModalBottomSheetContent(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .navigationBarsPadding(),
//                scanResult = uiState.scanResult,
//                onCloseClick = onCloseBottomSheetClick,
//                onRejectClicked = onCloseBottomSheetClick,
//                onAcceptClicked = onAcceptClicked
//            )
//        }
//    ) {
//        Box(
//            modifier = Modifier
//                .background(Color.Black)
//                .fillMaxSize()
//                .systemBarsPadding()
//        ) {
//            when (cameraPermissionState.status) {
//                PermissionStatus.Granted -> {
//                    MovplayTitleScanner(
//                        modifier = Modifier.fillMaxSize(),
//                        isScanningInProgress = uiState.scanningInProgress,
//                        errorText = errorText,
//                        onBitmapCaptured = onBitmapCaptured
//                    )
//                }
//                is PermissionStatus.Denied -> {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(MaterialTheme.spacing.medium),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Text(
//                            text = stringResource(R.string.camera_permission_rationale_info),
//                            textAlign = TextAlign.Center
//                        )
//                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
//                        OutlinedButton(onClick = { cameraPermissionState.launchPermissionRequest() }) {
//                            Text(
//                                modifier = Modifier.animateContentSize(),
//                                text = stringResource(R.string.camera_permission_rationale_request_permission_button_label)
//                            )
//                        }
//                    }
//                }
//            }
//
//            MovplayAppBar(
//                modifier = Modifier.align(Alignment.TopCenter),
//                title = null,
//                action = {
//                    IconButton(onClick = onBackClicked) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "go back",
//                            tint = MaterialTheme.colorScheme.primary
//                        )
//                    }
//                }
//            )
//        }
//    }
}