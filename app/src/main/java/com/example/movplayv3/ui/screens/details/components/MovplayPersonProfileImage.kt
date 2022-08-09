package com.example.movplayv3.ui.screens.details.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.size.Scale
import com.example.movplayv3.ui.components.items.MovplayErrorPresentableItem
import com.example.movplayv3.ui.components.items.MovplayLoadingPresentableItem
import com.example.movplayv3.ui.components.items.MovplayNoPhotoPresentableItem
import com.example.movplayv3.ui.screens.details.person.PersonDetailsState
import com.example.movplayv3.ui.theme.Size
import com.example.movplayv3.ui.theme.sizes
import com.example.movplayv3.utils.ImageUrlParser
import com.example.movplayv3.utils.TmdbImage

@Composable
fun MovplayPersonProfileImage(
    personDetailsState: PersonDetailsState,
    modifier: Modifier = Modifier,
    size: Size = MaterialTheme.sizes.presentableItemBig
) {
    Card(
        modifier = modifier
            .width(size.width)
            .height(size.height),
        shape = MaterialTheme.shapes.medium
    ) {
        when (personDetailsState) {
            is PersonDetailsState.Loading -> {
                MovplayLoadingPresentableItem(
                    modifier = Modifier.fillMaxSize()
                )
            }
            is PersonDetailsState.Error -> {
                MovplayErrorPresentableItem(
                    modifier = Modifier.fillMaxSize()
                )
            }

            is PersonDetailsState.Result -> {
                val profilePath = personDetailsState.details.profilePath

                if (profilePath != null) {
                    TmdbImage(
                        modifier = Modifier.fillMaxSize(),
                        imagePath = profilePath,
                        imageType = ImageUrlParser.ImageType.Profile
                    ) {
                        size(coil.size.Size.ORIGINAL)
                        scale(Scale.FILL)
                        crossfade(true)
                    }
                } else {
                    MovplayNoPhotoPresentableItem(
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}