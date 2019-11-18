package com.jvmori.topify.view.fragment.artistDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jvmori.topify.R
import com.jvmori.topify.Utils.ImageLoader
import com.jvmori.topify.Utils.ImageParams
import com.jvmori.topify.data.Resource
import com.jvmori.topify.data.response.top.Album
import com.jvmori.topify.data.response.top.ArtistItem
import com.jvmori.topify.databinding.ArtistDetailsBinding
import com.jvmori.topify.view.customViews.AlbumItem
import com.jvmori.topify.view.viewmodel.ArtistsDetailsViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.artist_details.*
import javax.inject.Inject

class FragmentArtistDetails : DaggerFragment() {

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var viewModel: ArtistsDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.let {
            viewModel = ViewModelProviders.of(it, factory).get(ArtistsDetailsViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        val binding  : ArtistDetailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.artist_details,
            container,
            false)
        val view = binding.root
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchCurrentArtist(arguments)

//        imageLoader.loadImageWithRoundedCorners(
//            viewModel.currentArtist?.getImageUrl(), profilePic, ImageParams(
//                5000F, 0F, 160, 160
//            )
//        )
        viewModel.fetchAlbums(viewModel.currentArtist?.id)
        viewModel.fetchPopularity(viewModel.currentArtist)
        viewModel.getAlbums().observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> loading()
                Resource.Status.SUCCESS -> albumsSuccess(it.data)
                Resource.Status.ERROR -> error()
            }
        })
    }

    private fun loading() {}
    private fun albumsSuccess(items: List<Album>?) {
        createArtistsAlbums(items ?: mutableListOf())
    }

    private fun error() {}

    private fun createArtistsAlbums(items: List<Album>) {
        popularAlbums.setRecyclerView(
            imageLoader,
            getAlbumViewItems(items)
        )
    }

    private fun getAlbumViewItems(items: List<Album>): List<AlbumItem> {
        val viewItems = mutableListOf<AlbumItem>()
        val albumSet = mutableSetOf<Album>()
        items.forEach {
            if (!albumSet.contains(it)) albumSet.add(it)
        }
        albumSet.forEach {
            viewItems.add(
                AlbumItem(
                    it.getMediumImageUrl(),
                    it.name,
                    it.releaseDate
                )
            )
        }
        return viewItems
    }

    private fun createGenres(artistItem: ArtistItem) {

    }

}