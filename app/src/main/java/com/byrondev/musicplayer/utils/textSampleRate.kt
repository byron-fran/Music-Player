package com.byrondev.musicplayer.utils

fun String.textSampleRate(audioBitDepth : Int) : String {
    return "$audioBitDepth Bit " + "/" + " ${this[0]}" + "${this[1]}" + if(this[2] != '0') { ".${this[2]}khz" } else { "khz" }

}
