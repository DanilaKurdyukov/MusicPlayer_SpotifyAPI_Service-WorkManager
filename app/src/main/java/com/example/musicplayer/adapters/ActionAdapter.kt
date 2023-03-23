package com.example.musicplayer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.models.Action
import com.google.android.material.textview.MaterialTextView

class ActionAdapter(val actions: List<Action>, val context: Context) :
    RecyclerView.Adapter<ActionAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtActionName = itemView.findViewById<MaterialTextView>(R.id.text_view_actionName)
        val actionImage = itemView.findViewById<ImageView>(R.id.image_view_actionImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ActionAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.action_item,parent,false))
    }

    override fun getItemCount(): Int {
       return actions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current: Action = actions[position]
        holder.txtActionName.text = current.name
        when(current.name){
            "History" -> holder.actionImage.setImageResource(R.drawable.baseline_history_24)
            "Last added" -> holder.actionImage.setImageResource(R.drawable.baseline_add_to_photos_24)
            "Most played" -> holder.actionImage.setImageResource(R.drawable.baseline_auto_graph_24)
            "Shuffle" -> holder.actionImage.setImageResource(R.drawable.baseline_shuffle_24)
        }
    }

}