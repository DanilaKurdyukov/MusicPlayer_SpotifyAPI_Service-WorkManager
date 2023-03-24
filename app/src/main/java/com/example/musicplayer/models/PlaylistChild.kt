package com.example.musicplayer.models

import com.google.gson.annotations.SerializedName

class PlaylistChild(var name: String, var images: List<Image>, var type: String, var tracks: PlaylistTrack,@SerializedName("owner") var playlistOwner: PlaylistOwner) {
}