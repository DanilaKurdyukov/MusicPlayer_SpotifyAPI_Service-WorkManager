package com.example.musicplayer.models

import com.google.gson.annotations.SerializedName

class Album(var artists: List<Artist>, @SerializedName("images") var songImages: List<Image>) {
}