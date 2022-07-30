package com.example.movplayv3.ui.components.sections

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.paging.compose.LazyPagingItems
import com.example.movplayv3.data.model.DetailPresentable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovplayPresentableTopSection(
    title: String,
    state: LazyPagingItems<out DetailPresentable>,
    modifier: Modifier = Modifier,
    showMoreButton: Boolean = true,
    scrollState: ScrollState? = null,
    scrollValueLimit: Float? = null,
    onPresentableClick: (Int) -> Unit = {},
    onMoreClick: () -> Unit = {}
) {
    val density = LocalDensity.current
    val pagerState = rememberPagerState()
    val isDark by rememberSaveable { mutableStateOf(true) }
    val contentColor by animateColorAsState(targetValue = if (isDark) Color.White else Color.Black)
    val selectedPresentable by derivedStateOf {
        val snapShot = state.itemSnapshotList
        if (snapShot.isNotEmpty()) snapShot.getOrNull(pagerState.currentPage) else null
    }
    val currentScrollValue = scrollState?.value
}