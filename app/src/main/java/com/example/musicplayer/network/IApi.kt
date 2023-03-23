package com.example.musicplayer.network

import com.example.musicplayer.models.Playlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApi {
    @GET("users/{user_id}/playlists/")
    fun getPlaylists(@Path("user_id") userId: String, @Query("access_token") accessToken: String) : Call<Playlist>
}