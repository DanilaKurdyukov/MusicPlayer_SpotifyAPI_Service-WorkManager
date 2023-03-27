package com.example.musicplayer.network

import com.example.musicplayer.models.Playlist
import com.example.musicplayer.models.Track
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApi {
    @GET("users/{user_id}/playlists/")
    fun getPlaylists(@Path("user_id") userId: String, @Query("access_token") accessToken: String) : Call<Playlist>
    @GET("playlists/{playlist_id}/tracks")
    fun getSongsByPlaylist(@Path("playlist_id") playlistId: String, @Query("access_token") accessToken: String) : Call<Track>
}