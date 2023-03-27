package com.example.musicplayer.models

import com.google.gson.annotations.SerializedName

class TrackInfo(@SerializedName("name") var songName: String, var album: Album, var artists: List<Artist>) {
}