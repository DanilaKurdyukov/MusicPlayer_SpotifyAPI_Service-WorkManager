package com.example.musicplayer.models

import com.google.gson.annotations.SerializedName

class Playlist(var href: String, var items: List<PlaylistChild>) {
}