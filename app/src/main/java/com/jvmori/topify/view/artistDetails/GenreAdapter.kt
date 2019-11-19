package com.jvmori.topify.view.artistDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jvmori.topify.databinding.GenreItemBinding
import com.jvmori.topify.view.adapters.BaseAdapter

class GenreAdapter(
    items: List<String>?
) : BaseAdapter<String>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GenreItemBinding.inflate(inflater)
        return GenreViewHolder(binding)
    }

    class GenreViewHolder(private val binding: GenreItemBinding) :
        BaseViewHolder<String>(binding.root) {
        override fun bindView(item: String?) {
            with(binding){
                genreName = item
                executePendingBindings()
            }
        }
    }
}