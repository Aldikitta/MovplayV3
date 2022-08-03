package com.example.movplayv3.ui.screens.search.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.window.PopupProperties
import com.example.movplayv3.R
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.partiallyAnnotatedString

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun MovplayQueryTextField(
    query: String?,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
    suggestions: List<String> = emptyList(),
    loading: Boolean = false,
    showClearButton: Boolean = false,
    voiceSearchAvailable: Boolean = false,
    cameraSearchAvailable: Boolean = false,
    info: @Composable () -> Unit = {},
    onQueryChange: (String) -> Unit = {},
    onQueryClear: () -> Unit = {},
    onKeyboardSearchClicked: (KeyboardActionScope.() -> Unit)? = null,
    onVoiceSearchClick: () -> Unit = {},
    onCameraSearchClick: () -> Unit = {},
    onSuggestionClick: (String) -> Unit = {}
) {
    var hasFocus by remember {
        mutableStateOf(false)
    }
    val suggestionsExpanded by derivedStateOf {
        hasFocus && suggestions.isNotEmpty()
    }
    Column(
        modifier = modifier.onFocusChanged { focusState -> hasFocus = focusState.hasFocus },
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
    ) {
        Box(modifier = modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                value = query.orEmpty(),
                onValueChange = onQueryChange,
                placeholder = {
                    Text(text = stringResource(R.string.search_placeholder))

                },
                trailingIcon = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AnimatedVisibility(
                            visible = loading,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            CircularProgressIndicator()
                        }
                        if (showClearButton) {
                            IconButton(onClick = onQueryClear) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "clear"
                                )
                            }
                        } else {
                            Row {
                                if (voiceSearchAvailable) {
                                    IconButton(onClick = onVoiceSearchClick) {
                                        Icon(
                                            imageVector = Icons.Filled.KeyboardVoice,
                                            contentDescription = "voice"
                                        )
                                    }
                                }
                                if (cameraSearchAvailable) {
                                    IconButton(onClick = onCameraSearchClick) {
                                        Icon(
                                            imageVector = Icons.Filled.CameraAlt,
                                            contentDescription = "camera"
                                        )
                                    }
                                }
                            }
                        }
                    }
                },
                keyboardActions = KeyboardActions(
                    onSearch = onKeyboardSearchClicked
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            SuggestionsDropdown(
                modifier = Modifier.fillMaxWidth(),
                expanded = suggestionsExpanded,
                query = query,
                suggestions = suggestions,
                onSuggestionClick = onSuggestionClick
            )
        }
        info()
    }
}

@Composable
private fun SuggestionsDropdown(
    query: String?,
    expanded: Boolean,
    modifier: Modifier = Modifier,
    suggestions: List<String> = emptyList(),
    onSuggestionClick: (String) -> Unit = {}
) {
    DropdownMenu(
        modifier = modifier,
        expanded = expanded,
        onDismissRequest = {},
        properties = PopupProperties(
            focusable = false,
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        suggestions.map { suggestion ->
            DropdownMenuItem(
                onClick = { onSuggestionClick(suggestion) },
                text = {
                    val annotatedString = partiallyAnnotatedString(
                        text = suggestion,
                        content = query.orEmpty()
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.History,
                            contentDescription = null,
                        )
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = MaterialTheme.spacing.small),
                            text = annotatedString,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            )
        }
    }
}