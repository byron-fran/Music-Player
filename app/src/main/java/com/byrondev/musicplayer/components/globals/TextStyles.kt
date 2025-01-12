package com.byrondev.musicplayer.components.globals

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.byrondev.musicplayer.ui.theme.Zinc40
import com.byrondev.musicplayer.ui.theme.textExtraSmall
import com.byrondev.musicplayer.ui.theme.textLarge
import com.byrondev.musicplayer.ui.theme.textMedium
import com.byrondev.musicplayer.ui.theme.textSmall

@Composable
fun TextSmall(text : String,modifier: Modifier = Modifier, color: Color = Zinc40,  ) {

    Text(
        text,
        color = color,
        maxLines = 1,
        minLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = textSmall,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TextExtraSmall(modifier: Modifier = Modifier,text : String, color: Color = Zinc40,  ) {

    Text(
        text,
        color = color,
        maxLines = 1,
        minLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = textExtraSmall,
        modifier = modifier
    )
}

@Composable
fun TextMedium(text : String ,modifier: Modifier = Modifier, color: Color = Color.White) {
    Text(
        text,
        color = color,
        style = textMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TextLarge(text : String ,modifier: Modifier = Modifier, color: Color = Color.White) {
    Text(
        text,
        color = color,
        style = textLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}
