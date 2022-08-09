package com.example.movplayv3.ui.components.others

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.size.Scale
import coil.size.Size
import com.example.movplayv3.data.model.Image
import com.example.movplayv3.utils.ImageUrlParser
import com.example.movplayv3.utils.TmdbImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovplayStillBrowser(
    stillPaths: List<Image>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    Column(modifier = modifier) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            count = stillPaths.count(),
            state = pagerState
        ) { page ->
            val stillImage = stillPaths.getOrNull(page)

            TmdbImage(
                modifier = Modifier.fillMaxWidth(),
                imagePath = stillImage?.filePath,
                imageType = ImageUrlParser.ImageType.Still,
                contentScale = ContentScale.FillWidth
            ) {
                size(Size.ORIGINAL)
                scale(Scale.FIT)
                crossfade(true)
            }
        }

        if (stillPaths.count() > 1) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
            )
        }
    }
}