package com.byrondev.musicplayer.utils.bitmap

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.byrondev.musicplayer.utils.decodeBitmapWithSubsampling

@RequiresApi(Build.VERSION_CODES.S)
fun getCoverArt(uri : String, context: Context) : Bitmap? {

    val art = getByteArray(uri, context)
    val bitMap = art?.let { decodeBitmapWithSubsampling(it, 600, 600) }
    return  bitMap

}

fun getByteArray(uri : String, context: Context) : ByteArray? {
    val retriever : MediaMetadataRetriever = MediaMetadataRetriever()

    try {
        retriever.setDataSource(context, Uri.parse(uri))
        return retriever.embeddedPicture
    }
    catch (error : Throwable) {
        return  null
    }
    finally {
        retriever.release()
    }
}
