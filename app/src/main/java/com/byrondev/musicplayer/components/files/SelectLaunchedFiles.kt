package com.byrondev.musicplayer.components.files

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.byrondev.musicplayer.ui.theme.Pink60
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.ProcessingState
import okio.Path.Companion.toPath

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SelectedLaunchedFiles (
    musicViewModels: MusicViewModels,
    content : @Composable (multipleFilesLauncher : ManagedActivityResultLauncher<Uri?, Uri?>) -> Unit
) {

    val context = LocalContext.current
    val processingState by musicViewModels.processingState.collectAsState()
    var selectedFiles by remember { mutableStateOf<List<Uri?>>(emptyList()) }

    val multipleFilesLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree()
    ) { uriFile ->
        if (uriFile != null) {

            try {
                context.contentResolver.takePersistableUriPermission(
                    uriFile,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )

                val childrenUri = DocumentsContract.buildChildDocumentsUriUsingTree(
                    uriFile,
                    DocumentsContract.getTreeDocumentId(uriFile)
                )
                val newFiles = mutableListOf<Uri>()
                val cursor = context.contentResolver.query(
                    childrenUri,
                    arrayOf(
                        DocumentsContract.Document.COLUMN_DOCUMENT_ID,
                        DocumentsContract.Document.COLUMN_MIME_TYPE,
                        DocumentsContract.Document.COLUMN_DISPLAY_NAME,

                        ),
                    null, null, null
                )

                cursor?.use {
                    while (it.moveToNext()) {
                        val mimeType = it.getString(it.getColumnIndexOrThrow(DocumentsContract.Document.COLUMN_MIME_TYPE))
                        val documentId = it.getString(it.getColumnIndexOrThrow(DocumentsContract.Document.COLUMN_DOCUMENT_ID))
                        // Filters only files audio
                        if (mimeType.startsWith("audio/")) {
                            val uri = DocumentsContract.buildChildDocumentsUriUsingTree(uriFile, documentId)
                            newFiles.add(uri)

                        }
                    }
                }

                selectedFiles = selectedFiles + newFiles
            } catch (error: Throwable) {
                Log.e("error uri", "${error.message}")
            }
        }
    }

    LaunchedEffect(selectedFiles) {
        if (selectedFiles.isNotEmpty()) {
            for (uri in selectedFiles) {
                uri?.let { musicViewModels.addTask(it,) }
            }
        }
    }

    Column {
        val toast = Toast.makeText(context, "Added successful", Toast.LENGTH_SHORT)

        content(multipleFilesLauncher)
        Column {
            when (processingState) {
                is ProcessingState.Idle -> { /* Add text if it necessary */ }
                is ProcessingState.Processing -> {

                    val currentTask = (processingState as ProcessingState.Processing).currentTask
                    val fileName = currentTask.path?.removeSuffix("/children")?.toPath()?.name

                        Dialog(onDismissRequest =  {},
                        ) {
                            Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                                CircularProgressIndicator(color = Pink60, modifier = Modifier.background(Color.Transparent).size(50.dp))
                                Text("Adding $fileName", color=Color.White)
                            }
                        }
                }

                is ProcessingState.Completed -> {
                   toast.show()
                }
            }
        }
    }
}