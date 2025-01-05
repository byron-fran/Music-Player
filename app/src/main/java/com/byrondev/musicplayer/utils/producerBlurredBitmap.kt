package com.byrondev.musicplayer.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun produceBlurredBitmap(bitmap: Bitmap?, context: Context): Bitmap? {

    if(bitmap != null ) {
        return withContext(Dispatchers.IO) {
            Glide.with(context)
                .asBitmap()
                .load(bitmap)
                .apply(
                    RequestOptions()
                        .transform(
                            CenterCrop(),
                            BlurTransformation(25)
                        )
                )
                .submit()
                .get()
        }
    }
     return  null
}