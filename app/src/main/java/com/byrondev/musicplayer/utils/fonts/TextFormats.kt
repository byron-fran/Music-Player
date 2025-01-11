package com.byrondev.musicplayer.utils.fonts



fun String.formatYear () : String {

    return  this.substringBefore("-").substringBefore(";")
}
