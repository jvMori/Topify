package com.jvmori.topify.view.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jvmori.topify.R
import com.jvmori.topify.Utils.ImageLoader
import com.jvmori.topify.data.response.top.Track
import kotlinx.android.synthetic.main.top_track.view.*

class TopTracksAdapter(
    tracks : List<Track>?,
    private val imageLoader : ImageLoader
) : BaseAdapter<Track>(tracks) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Track> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.top_track, parent, false)
        return TopTracksViewHolder(view, imageLoader)
    }

    class TopTracksViewHolder(itemView: View, private val imageLoader : ImageLoader) : BaseViewHolder<Track>(itemView){
        override fun bindView(item: Track?) {
            itemView.apply {
                item?.let {item ->
                    title.text = item.name
                    artist.text = item.let { it.artists[0].name }
                    length.text = item.getDurationMinutes()
                    index.text = item.getIndex(adapterPosition)
                    imageLoader.loadImage(item.album.images[1].url, icon)
                }
            }
        }
    }
}