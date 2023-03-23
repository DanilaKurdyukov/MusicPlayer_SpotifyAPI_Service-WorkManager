package com.example.musicplayer.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppData {

    companion object {
        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit? {
            try{
                retrofit =Retrofit.Builder()
                    .baseUrl("https://api.spotify.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                return retrofit!!
            }
            catch (e: Exception){
                e.message
            }
            return null
        }
    }

}