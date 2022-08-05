package com.example.movplayv3.ui.components.texts

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.movplayv3.R

@Composable
fun MovplayExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    postfixColor: Color = MaterialTheme.colorScheme.primary,
    minLines: Int = 3
) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    val textStyle = MaterialTheme.typography.bodyLarge
    var finalText by remember {
        mutableStateOf(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
//                        color = Color.Red,
//                        fontSize = 12.sp
                    )
                ) {
                    append(text)
                }
            }
        )
    }

    val postfixText = stringResource(if (isExpanded) R.string.show_less else R.string.show_more)

    val textLayoutResult = textLayoutResultState.value

    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        if (isExpanded) {
            finalText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
//                        color = Color.Green,

                    )
                ) {
                    append(text)
                }
                withStyle(
                    style = SpanStyle(
                        color = postfixColor,
//                        fontSize = 12.sp
                    )
                ) {
                    append("  $postfixText")
                }
            }
        } else {
            if (textLayoutResult.hasVisualOverflow) {
                val lastCharIndex = textLayoutResult.getLineEnd(minLines - 1)
                val adjustedText = text
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(postfixText.length)
                    .dropLastWhile { it in setOf(' ', '.') }

                finalText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
//                            color = Color.Blue,
//                            fontSize = 12.sp
                        )
                    ) {
                        append("$adjustedText… ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = postfixColor,
//                            fontSize = 12.sp
                        )
                    ) {
                        append(postfixText)
                    }
                }

                isClickable = true
            }
        }
    }

    Text(
        text = finalText,
        maxLines = if (isExpanded) Int.MAX_VALUE else minLines,
        onTextLayout = { textLayoutResultState.value = it },
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                enabled = isClickable,
                indication = null
            ) { isExpanded = !isExpanded }
            .animateContentSize(),
    )
}