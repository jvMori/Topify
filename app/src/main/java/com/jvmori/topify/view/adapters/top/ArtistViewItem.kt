package com.jvmori.topify.view.adapters.top

import com.jvmori.topify.R
import com.jvmori.topify.data.response.top.ArtistItem
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

class ArtistViewItem (private val artist: ArtistItem) : Item<ViewHolder>() {

    override fun getLayout(): Int {
        return  R.layout.top_artist
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}