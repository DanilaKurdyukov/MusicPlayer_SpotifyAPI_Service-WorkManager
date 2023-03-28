package com.example.musicplayer

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

class MusicService : Service() {

    private val clientId = "M2YwM2YxMmM5YjA5NGY4ZmEwOTg4ODg4M2RjYTBjOTc="
    private val redirectUri = "https%3A%2F%2Fapi-university.com%2F"
    private var spotifyAppRemote: SpotifyAppRemote? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val connectionParams = ConnectionParams.Builder(clientId)
            .setRedirectUri(redirectUri)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(this, connectionParams, object : Connector.ConnectionListener {
            override fun onConnected(appRemote: SpotifyAppRemote) {
                spotifyAppRemote = appRemote
                Log.d("MusicService", "Connected! Yay!")
                // Now you can start interacting with App Remote
                connected()
            }

            override fun onFailure(throwable: Throwable) {
                Log.e("MusicService", throwable.message, throwable)
                // Something went wrong when attempting to connect! Handle errors here
            }
        })
        return super.onStartCommand(intent, flags, startId)
    }



    override fun onDestroy() {
        super.onDestroy()
        spotifyAppRemote?.let {
            SpotifyAppRemote.disconnect(it)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun connected(){
        // Play a playlist
        spotifyAppRemote?.playerApi?.play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL")
    }
}