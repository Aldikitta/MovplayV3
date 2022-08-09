package com.example.movplayv3.ui.components.lists

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movplayv3.data.model.ProviderSource
import com.example.movplayv3.ui.components.chips.MovplayLogoChip
import com.example.movplayv3.ui.theme.spacing
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MovplayProvidersSourceList(
    selectedProvidersSources: List<ProviderSource>,
    availableProvidersSources: List<ProviderSource>,
    modifier: Modifier = Modifier,
    onProviderSourceSelected: (ProviderSource) -> Unit = {}
) {
    FlowRow(
        modifier = modifier,
        mainAxisSpacing = MaterialTheme.spacing.extraSmall,
        crossAxisSpacing = MaterialTheme.spacing.extraSmall
    ) {
        availableProvidersSources.map { providerSource ->
            val selected = providerSource in selectedProvidersSources

            MovplayLogoChip(
                logoPath = providerSource.logoPath,
                text = providerSource.providerName,
                selected = selected
            ) {
                onProviderSourceSelected(providerSource)
            }
        }
    }
}