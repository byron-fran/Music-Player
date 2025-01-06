package com.byrondev.musicplayer.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory


fun decodeBitmapWithSubsampling(byteArray: ByteArray, reqWidth: Int, reqHeight: Int): Bitmap? {

    // Configura opciones para solo leer las dimensiones de la imagen
    val options = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
    }

    // Leer las dimensiones sin cargar la imagen en memoria
    BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, options)

    // Calcular el factor de reducción (inSampleSize)
    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

    // Configurar opciones para cargar la imagen reducida
    options.inJustDecodeBounds = false

    // Decodificar la imagen con la reducción aplicada
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, options)
}

fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    val height = options.outHeight
    val width = options.outWidth
    var inSampleSize = 1

    if (height > reqHeight || width > reqWidth) {
        val halfHeight = height / 2
        val halfWidth = width / 2

        // Incrementa inSampleSize en potencias de 2 mientras mantenga la resolución requerida
        while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
            inSampleSize *= 2
        }
    }
    return inSampleSize
}