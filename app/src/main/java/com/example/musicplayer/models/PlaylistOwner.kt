package com.example.musicplayer.models

import com.google.gson.annotations.SerializedName

class PlaylistOwner(@SerializedName("display_name") var name: String, @SerializedName("href") var ownerUrl: String) {
}