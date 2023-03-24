package com.example.musicplayer.models

import com.google.gson.annotations.SerializedName

class PlaylistTrack(@SerializedName("href") var tracksUrl: String, @SerializedName("total") var count: Int) {
}