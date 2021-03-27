package com.nickmirosh.podbook.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nickmirosh.podbook.R
import com.nickmirosh.podbook.network.SearchResult

class HomeAdapter(val onInteractionListener: InteractionListener) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    interface InteractionListener {
        fun onEpisodeClick(episodeId: String)
    }

    private var dataSet: List<SearchResult>? = null

    fun setData(dataSet: List<SearchResult>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_podcast_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = dataSet!![position]

        with(viewHolder) {
            name.text = item.title
            name.setOnClickListener {
                onInteractionListener.onEpisodeClick(item.id)
            }
        }
    }

    override fun getItemCount() = dataSet?.size ?: 0
}
