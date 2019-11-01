package com.jvmori.topify.view.adapters.top

import com.jvmori.topify.R
import com.jvmori.topify.Utils.ImageLoader
import com.jvmori.topify.data.response.top.ArtistItem
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.loading.view.*
import kotlinx.android.synthetic.main.top_artist.view.*

class ArtistViewItem (
    private val imageLoader : ImageLoader,
    private val artist: ArtistItem,
    private var onClick : (artist: ArtistItem) -> Unit?
) : Item<ViewHolder>() {

    override fun getLayout(): Int {
        return  R.layout.top_artist
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
       viewHolder.itemView.apply {
           artistName.text = artist.name
           genres.text = artist.genresToString(2)
           followers.text = artist.followersToString()
           imageLoader.loadImage(artist.getImageUrl(), artistImage)
           setOnClickListener {
               onClick(artist)
           }
       }
    }


}