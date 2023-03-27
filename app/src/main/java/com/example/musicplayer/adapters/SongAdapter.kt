package com.example.musicplayer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.adapters.SongAdapter.ViewHolder
import com.example.musicplayer.models.TrackChild
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso

class SongAdapter(var tracks: List<TrackChild>, var context: Context) :
    RecyclerView.Adapter<ViewHolder>() {



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val songImage = itemView.findViewById<ShapeableImageView>(R.id.image_view_songImageSmall)
        val txtSongName = itemView.findViewById<MaterialTextView>(R.id.text_view_songNamePrimary)
        val txtSinger = itemView.findViewById<MaterialTextView>(R.id.text_view_singer)
        val btnSettings = itemView.findViewById<ImageView>(R.id.image_button_settingsSong)
        val divider = itemView.findViewById<View>(R.id.divider_view_song)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.song_item,parent,false))
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current: TrackChild = tracks[position]
        holder.txtSongName.text = shortSongName(current.trackInfo.songName)
        Picasso.get().load(current.trackInfo.album.songImages[0].url).into(holder.songImage)
        if (position==tracks.size-1){
            holder.divider.visibility = View.GONE
        }
    }

    private fun shortSongName(longName: String) : String{
        if (longName.length>32) {
            return "${longName.substring(0, 32)}...";
        }
        return longName
    }

}