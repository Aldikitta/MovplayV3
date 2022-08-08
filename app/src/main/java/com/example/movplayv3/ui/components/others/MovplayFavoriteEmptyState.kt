package com.example.movplayv3.ui.components.others

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.*
import com.example.movplayv3.R
import com.example.movplayv3.data.model.FavoriteType
import com.example.movplayv3.ui.theme.spacing

@Composable
fun MovplayFavoriteEmptyState(
    type: FavoriteType,
    modifier: Modifier,
    onButtonClick: () -> Unit = {}
) {
    @StringRes
    val infoTextResId = when (type) {
        FavoriteType.Movie -> R.string.favourite_empty_movies_info_text
        FavoriteType.TvShow -> R.string.favourite_empty_tv_series_info_text
    }

    @StringRes
    val buttonLabelResId = when (type) {
        FavoriteType.Movie -> R.string.favourite_empty_navigate_to_movies_button_label
        FavoriteType.TvShow -> R.string.favourite_empty_navigate_to_tv_series_button_label
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_empty))
    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR_FILTER,
            value = PorterDuffColorFilter(
                MaterialTheme.colorScheme.primary.copy(alpha = 0.7f).toArgb(),
                PorterDuff.Mode.SRC_ATOP
            ),
            keyPath = arrayOf(
                "**"
            )
        )
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = Modifier.size(200.dp),
            composition = composition,
            speed = 0.5f,
            iterations = LottieConstants.IterateForever,
            dynamicProperties = dynamicProperties
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Text(
            text = stringResource(infoTextResId),
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        TextButton(onClick = onButtonClick) {
            Text(modifier = Modifier.animateContentSize(), text = stringResource(buttonLabelResId))
        }
    }
}