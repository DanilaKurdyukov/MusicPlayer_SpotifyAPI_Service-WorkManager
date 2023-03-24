package com.example.musicplayer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.adapters.PlaylistAdapter
import com.example.musicplayer.models.Playlist
import com.example.musicplayer.models.PlaylistChild
import com.example.musicplayer.network.AppData
import com.example.musicplayer.network.IApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MyPlaylistsFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_playlists, container, false)
    }

    var playlistsChild: ArrayList<PlaylistChild> = ArrayList()
    var playlistAdapter : PlaylistAdapter? = null
    var playlistRecycler : RecyclerView? = null

    var api : IApi? = null

    val token = "BQDqYFLtrh1PDEYSioLxArF_Xfj4cD6RMxRf1QKrz_TNFcIEUjX4IUeOK2zAjfJOwXnluQEgVqh8VW9yBCLBIDal1W_r8f0rG2avRAfvMSyXrKyeNFBR0iVY4kUmQYvxnYsL24prnibHEWqzp9iMdyeKZjJdlN0vFVjXI4UdRbAtgj0Kujc3V2mPcmyZZ72-49uzCzgfjiXCsime"
    val userId = "hqjjh9g46r5kgvvydj3dcan88"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        api =AppData.getClient()?.create(IApi::class.java)
        init(view)
        getPlaylists()
    }



    private fun init(view: View) {
        playlistRecycler = view.findViewById<RecyclerView>(R.id.recycler_view_playlists)
    }

    private fun getPlaylists(){
        val call = api?.getPlaylists(userId,token)
        call?.enqueue(object: Callback<Playlist>{
            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                var playlistMain = response.body()
                if(playlistMain!=null){
                    for(item in playlistMain?.items!!){
                        playlistsChild?.add(item)
                    }
                }
                playlistAdapter = PlaylistAdapter(playlistsChild!!,requireContext())
                playlistRecycler?.adapter = playlistAdapter!!
            }

            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                t.message
                call.cancel()
            }

        })
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            MyPlaylistsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}