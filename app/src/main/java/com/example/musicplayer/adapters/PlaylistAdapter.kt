package com.example.musicplayer.adapters

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.models.Playlist
import com.example.musicplayer.models.PlaylistChild
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso

class PlaylistAdapter(var playlists: List<PlaylistChild>, var context: Context) : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imagePrimary = itemView.findViewById<ImageView>(R.id.image_view_playListImagePrimary)
        val txtPlaylistPrimaryName = itemView.findViewById<MaterialTextView>(R.id.text_view_playlistNamePrimary)
        val divider = itemView.findViewById<View>(R.id.divider_view)
        val txtPlaylistTypeOwner = itemView.findViewById<MaterialTextView>(R.id.text_view_playlistTypeOwner)
        val txtTracksCount = itemView.findViewById<MaterialTextView>(R.id.text_view_tracksCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PlaylistAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.playlist_item,parent,false))
    }

    override fun getItemCount(): Int {
        return playlists.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current: PlaylistChild = playlists[position]
        holder.txtPlaylistPrimaryName.text = current.name
        holder.txtPlaylistTypeOwner.text = "${current.type} | ${current.playlistOwner.name}"
        holder.txtTracksCount.text = current.tracks.count.toString()
        Picasso.get().load(current.images[0].url).into(holder.imagePrimary)
        if(position==itemCount-1){
            holder.divider.visibility = View.GONE
        }
    }

}