package com.example.musicplayer.models

import com.google.gson.annotations.SerializedName

class Album(@SerializedName("images") var songImages: List<Image>) {
}