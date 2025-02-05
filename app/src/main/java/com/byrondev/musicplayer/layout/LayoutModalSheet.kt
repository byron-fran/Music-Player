package com.byrondev.musicplayer.layout

import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.ui.theme.Slate80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetModalLayout(
    showModalSheet : MutableState<Boolean>,
    content : @Composable () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    if (showModalSheet.value) {
        ModalBottomSheet (
            modifier = Modifier.height(350.dp),
            sheetState = sheetState,
            onDismissRequest = { showModalSheet.value = false },
            containerColor = Slate80
        ){
            content()
        }
    }
}