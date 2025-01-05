package com.byrondev.musicplayer.utils.metadata

import android.content.Context
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.byrondev.musicplayer.data.models.Album
import com.byrondev.musicplayer.data.models.Artist
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.utils.bytearray.bitmapToByteArray

data class AudioMetadata (
    val album : Album,
    val song : Song,
    val artist : Artist
)

@RequiresApi(Build.VERSION_CODES.S)
fun getAudioMetadata (context : Context, uri : Uri)  : AudioMetadata {

    val retriever = MediaMetadataRetriever()
    retriever.setDataSource(context, uri)

    val art = retriever.embeddedPicture
    val albumArtBitmap = art?.let { BitmapFactory.decodeByteArray(it, 0, it.size) }
    val bitDepth = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITS_PER_SAMPLE) ?: ""
    val sampleRate = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_SAMPLERATE) ?: ""
    val bitrate = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE) ?: ""
    val duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION) ?: ""
    val trackNumber = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER)
    val diskNumber = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DISC_NUMBER) ?: "1"
    val composer = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_COMPOSER)
    val title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE) ?: "Unknown"
    val artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST) ?: "Unknown"
    val album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM) ?: "Unknown"
    val dateRelease = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE)
    val genre = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE)
    val albumArtist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUMARTIST)
    val audioCodec = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE) ?: ""
    val cdTrackNumber = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER) ?: ""
    val qualityAudio: Int = when (bitDepth) {
        "24" -> 3
        "16" -> 2
        else -> 1
    }

    return  AudioMetadata(
        Album (
            title = album,
            artist = artist.substringBefore(","),
            year = dateRelease,
            genres = genre,
            cover = bitmapToByteArray(albumArtBitmap),
            albumArtist = albumArtist,
            quality = qualityAudio
        ),
        Song(
            title = title,
            artist = artist,
            audioBitDepth = bitDepth.toInt(),
            sampleRate = sampleRate.toInt(),
            bitRate = bitrate.toInt(),
            cover = bitmapToByteArray(albumArtBitmap),
            album = album,
            year = dateRelease,
            duration = duration.toLong(),
            trackNumber = getTrackNumber(trackNumber),
            uri = uri.toString(),
            composer = composer,
            genre = genre,
            disk = getDiskNumber(diskNumber),
            audioCodec = audioCodec.substringAfter("/"),
            ),
        Artist(name = artist.substringBefore(",").substringBefore(" & ")
        )
    )
}


fun getTrackNumber(trackNumber: String?): Int {
    return trackNumber?.substringBefore("/")?.toIntOrNull() ?: 0
}

fun getDiskNumber(diskNumber : String) : Int {
        if(diskNumber.contains("/")) {
            val disk = diskNumber.substringAfter("/")
           return disk.toInt()
        }
       return  diskNumber.toInt()

}
