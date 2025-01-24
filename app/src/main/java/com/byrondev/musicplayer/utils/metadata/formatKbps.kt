package com.byrondev.musicplayer.utils.metadata

fun formatKbps(kbps : Int) : String {

    return if(kbps.toString().length == 6){
        kbps.toString().substring(0, 3)
    }
    else{
        kbps.toString().substring(0, 4)
    }
}

