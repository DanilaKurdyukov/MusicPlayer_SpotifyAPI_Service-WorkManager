package com.example.musicplayer.fragments

import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.MusicService
import com.example.musicplayer.R
import com.example.musicplayer.adapters.PlaylistAdapter
import com.example.musicplayer.adapters.SongAdapter
import com.example.musicplayer.models.Playlist
import com.example.musicplayer.models.PlaylistChild
import com.example.musicplayer.models.Track
import com.example.musicplayer.models.TrackChild
import com.example.musicplayer.network.AppData
import com.example.musicplayer.network.IApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SongsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    var songsRecycler: RecyclerView? = null
    var songAdapter: SongAdapter? = null
    var songs: ArrayList<TrackChild> = ArrayList()

    val token = "BQCK1iNVOJxuqQFb0CsozzZPwAqtD9fjv-ILrzhMghzSMA6d2oSIlocjuGy73IryT7DSmiXO5eDcB35UCXtxdRKMiV7F7JMA1RStKleA7gNCwnDZR-CdNGrJCdPwouEX9nDLll9a4STdajBTziN1JdQlNudGSizVAn_m8u05sEiT_4PBNjxSDgvHClHEXEsiBZSd4-edprnWXTZ8"

    var api : IApi? = null

    var currentPlaylist: PlaylistChild? = null

    var imageViewIsPlaylistCollaborative: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_songs, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentPlaylist = arguments?.getParcelable("Playlist")

        api = AppData.getClient()?.create(IApi::class.java)
        init(view)
        getPlaylists()

        when(currentPlaylist?.isPlaylistCollaborative){
            false -> imageViewIsPlaylistCollaborative?.setImageResource(R.drawable.baseline_lock_24)
            true -> imageViewIsPlaylistCollaborative?.setImageResource(R.drawable.baseline_lock_open_24)
            else -> imageViewIsPlaylistCollaborative?.setImageDrawable(null)
        }

    }

    private fun getPlaylists(){
       val call = api?.getSongsByPlaylist(currentPlaylist?.playlistId!!, token)
        call?.enqueue(object: Callback<Track>{
            override fun onResponse(call: Call<Track>, response: Response<Track>) {
                if (response.isSuccessful){
                    var trackMain = response.body()
                    for(item in trackMain?.items!!){
                        songs.add(item)
                    }
                    songAdapter = SongAdapter(songs,requireContext())
                    songsRecycler?.adapter = songAdapter
                }
            }

            override fun onFailure(call: Call<Track>, t: Throwable) {
                t.message
                call.cancel()
            }

        })
    }

    private fun init(view: View) {
        songsRecycler = view.findViewById<RecyclerView>(R.id.recycler_view_songsByPlaylist)
        imageViewIsPlaylistCollaborative = view.findViewById(R.id.image_view_isPlaylistCollaborative)
        val btnPlay = view.findViewById<Button>(R.id.button_play)
        btnPlay.setOnClickListener(View.OnClickListener{
            activity?.startService(Intent(context,MusicService::class.java))
        })

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SongsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}