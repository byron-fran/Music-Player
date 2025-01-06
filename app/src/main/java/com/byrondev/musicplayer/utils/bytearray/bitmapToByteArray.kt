package com.byrondev.musicplayer.utils.bytearray
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream


fun bitmapToByteArray(bitmap: Bitmap?, maxWidth: Int = 500, maxHeight: Int = 500) : ByteArray? {

    val resizedBitmap = bitmap?.let { resizeBitmap(it, maxWidth, maxHeight) }
    val outputStream = ByteArrayOutputStream()
    resizedBitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    return outputStream.toByteArray()


}

fun resizeBitmap(bitmap: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {

    val width = bitmap.width
    val height = bitmap.height
    val scaleFactor = minOf(maxWidth.toFloat() / width, maxHeight.toFloat() / height)
    val scaledWidth = (width * scaleFactor).toInt()
    val scaledHeight = (height * scaleFactor).toInt()
    return Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true)

}
