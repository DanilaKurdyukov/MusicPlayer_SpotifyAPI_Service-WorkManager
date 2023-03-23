package com.example.musicplayer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.adapters.ActionAdapter
import com.example.musicplayer.models.Action
import com.google.android.material.textview.MaterialTextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
    }

    private fun init(view: View) {

        //header layout
        val headerLayout = view.findViewById<View>(R.id.header_layout)
        val txtUserName = headerLayout.findViewById<MaterialTextView>(R.id.text_view_userName)
        txtUserName.text = "Дане4ка"

        //action layout
        val actionLayout = view.findViewById<View>(R.id.action_layout)
        val recyclerView = actionLayout.findViewById<RecyclerView>(R.id.recycler_view_actions)
        val actionAdapter = ActionAdapter(getActions(),requireContext())
        recyclerView.adapter = actionAdapter


    }

    private fun getActions() : List<Action>{
        return listOf(Action("History"), Action("Last added"), Action("Most played"), Action("Shuffle"))
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}