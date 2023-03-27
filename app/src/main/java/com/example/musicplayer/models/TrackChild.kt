package com.example.musicplayer.models

import com.google.gson.annotations.SerializedName

class TrackChild(@SerializedName("track") var trackInfo: TrackInfo) {
}