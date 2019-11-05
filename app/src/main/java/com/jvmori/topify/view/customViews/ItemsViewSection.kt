package com.jvmori.topify.view.customViews

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jvmori.topify.R
import com.jvmori.topify.Utils.ImageLoader
import com.jvmori.topify.Utils.ImageParams
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.android.synthetic.main.items_view_section.view.*

class ItemsViewSection(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.items_view_section, this)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ItemsViewSection,
            0,
            0
        ).apply {
            try {
                sectionName.text = getString(R.styleable.ItemsViewSection_sectionName)
            } finally {
                recycle()
            }
        }
    }

    fun setSectionName(title: String) {
        sectionName.text = title
    }

    fun setRecyclerView(imageLoader: ImageLoader, items : List<AlbumItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>()
        items.forEach {
            groupAdapter.add(
                AlbumViewItem(imageLoader, it)
            )
        }
        itemsRv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = groupAdapter
        }
    }
}

class AlbumViewItem(
    private val imageLoader : ImageLoader,
    private val albumItem: AlbumItem
) : Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.album_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.apply {
            imageLoader.loadImageWithRoundedCorners(
                albumItem.url,
                albumPhoto,
                ImageParams(
                    15F,
                    0F,
                    100,
                    100
                )
            )
            albumTitle.text = albumItem.albumName
            albumYear.text = albumItem.year
        }
    }
}

data class AlbumItem (
    var url : String?,
    var albumName : String,
    var year : String
)