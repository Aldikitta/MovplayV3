package com.example.movplayv3.ui.components.sections

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import com.example.movplayv3.data.model.Video
import com.example.movplayv3.data.model.VideoSite
import com.example.movplayv3.data.model.getThumbnailUrl
import com.example.movplayv3.ui.components.texts.MovplaySectionLabel
import com.example.movplayv3.ui.theme.sizes
import com.example.movplayv3.ui.theme.spacing

@Composable
fun MovplayVideosSection(
    videos: List<Video>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    title: String? = null,
    onVideoClicked: (Video) -> Unit = {}
) {
    Column(modifier = modifier) {
        title?.let { title ->
            MovplaySectionLabel(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium),
                text = title
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            contentPadding = contentPadding
        ) {
            items(videos) { video ->
                VideoItem(
                    video = video,
                    onVideoClick = {
                        onVideoClicked(video)
                    }
                )
            }
        }
    }
}

@Composable
fun VideoItem(
    video: Video,
    modifier: Modifier = Modifier,
    onVideoClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(MaterialTheme.sizes.videoItem.width)
            .height(MaterialTheme.sizes.videoItem.height),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary.copy(0.5f)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onVideoClick() },
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = video.getThumbnailUrl())
                        .apply(block = fun ImageRequest.Builder.() {
                            size(Size.ORIGINAL)
                            scale(Scale.FILL)
                            crossfade(true)
                        }).build()
                ),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
            SiteIcon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        top = MaterialTheme.spacing.extraSmall,
                        end = MaterialTheme.spacing.extraSmall
                    ),
                site = video.site
            )
        }
    }
}

@Composable
private fun SiteIcon(site: VideoSite, modifier: Modifier = Modifier) {
    @DrawableRes
    val drawableRes = when (site) {
        VideoSite.YouTube -> com.example.movplayv3.R.drawable.ic_youtube
        VideoSite.Vimeo -> com.example.movplayv3.R.drawable.ic_vimeo
    }

    Image(
        modifier = modifier,
        painter = painterResource(drawableRes),
        contentDescription = null
    )
}