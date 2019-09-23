package com.jvmori.topify.view.adapters.createdPlaylist

import com.jvmori.topify.R
import com.jvmori.topify.data.response.top.Track
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.created_playlist_item.view.*

class TrackItem(private val track : Track) : Item<ViewHolder>() {

    override fun getLayout(): Int {
        return R.layout.created_playlist_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.songTitle.text = track.name
        viewHolder.itemView.artist.text = track.artists[0].name
        viewHolder.itemView.album.text = track.album.name
        viewHolder.itemView.number.text = (position + 1).toString()
    }

}