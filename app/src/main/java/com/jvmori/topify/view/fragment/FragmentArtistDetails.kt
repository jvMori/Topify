package com.jvmori.topify.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jvmori.topify.R
import com.jvmori.topify.Utils.ImageLoader
import com.jvmori.topify.Utils.artistDetailsKey
import com.jvmori.topify.data.response.top.ArtistItem
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.artist_details.*
import javax.inject.Inject

class FragmentArtistDetails : DaggerFragment() {

    private var artistItem : ArtistItem? = null

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.artist_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            artistItem = it.getParcelable(artistDetailsKey)
            imageLoader.loadImageWithRoundedCorners(artistItem?.getImageUrl(), profilePic, 100F, 0f)
        }

    }
}