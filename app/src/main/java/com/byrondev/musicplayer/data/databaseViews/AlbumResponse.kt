package com.byrondev.musicplayer.data.databaseViews

import androidx.room.DatabaseView

@DatabaseView("""
        SELECT
        a.id,a.title,a.artist, a.year, a.artist_id AS artistId,a.tracks_total as tracksTotal,
        COUNT(CASE WHEN s.audio_bit_depth >= 24 THEN 1 ELSE NULL END) as numOfHiresQuality,
        COUNT(s.id) as numTracks,
        CASE WHEN s.uri NOT NULL THEN s.uri ELSE "" END as cover
        FROM albums a
        LEFT JOIN songs s ON s.album_id = a.id
        GROUP BY a.id ORDER BY a.year DESC
    """)
data class AlbumResponse (
    val id : Int = 0,
    val title : String = "",
    val artist : String = "",
    val year : String = "",
    val artistId : Int = 0,
    val cover : String = "",
    val numOfHiresQuality : Int = 0,
    val tracksTotal : Int? = 0,
    val numTracks : Int? = 0,
)
