package com.jvmori.topify.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jvmori.topify.R
import com.jvmori.topify.Utils.*

import com.jvmori.topify.data.Resource
import com.jvmori.topify.data.db.entity.TopTracksResponse
import com.jvmori.topify.data.response.top.Image
import com.jvmori.topify.data.response.top.Track
import com.jvmori.topify.view.adapters.top.TrackItem
import com.jvmori.topify.view.viewmodel.CreateTopViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.error_layout.view.*
import kotlinx.android.synthetic.main.fragment_top_details.*
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentTopDetails : DaggerFragment() {

    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var topViewModel: CreateTopViewModel
    private lateinit var topTracksResponse: TopTracksResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topViewModel = ViewModelProviders.of(this, factory).get(CreateTopViewModel::class.java)

        getTopTracksResponse()?.let { topTracks ->
            topTracksResponse = topTracks
            arguments?.let {
                val name = it.getString(playlistNameKey) ?: getString(R.string.topPlaylistName)
                val description = it.getString(playlistDescriptionKey) ?: getString(R.string.topPlaylistDescription)
                topViewModel.createPlaylistAndAddTracks(name, description, topTracks)
            }
        }
    }

    private fun getTopTracksResponse(): TopTracksResponse? {
        return (arguments?.getParcelable(topDetailsKey)) as TopTracksResponse?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayPlaylist()
    }

    private fun displayPlaylist() {
        topViewModel.getPlaylistCoverImage().observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> showProgressBar()
                Resource.Status.SUCCESS -> {
                    hideProgressBar()
                    showImage(it)
                    showListOfTracks(topTracksResponse.tracks)
                }
                Resource.Status.ERROR -> showError(it.message)
            }
        })
    }

    private fun showImage(it: Resource<List<Image>>) {
        imageLoader.loadImage(
            it.data?.let { list ->
                list[0].url
            },
            playlistsCoverImg
        )
    }

    private fun showListOfTracks(tracks: List<Track>) {
        val adapter = GroupAdapter<ViewHolder>()
        tracks.forEach {
            adapter.add(TrackItem(it))
        }
        playlistRecyclerView.layoutManager =
            LinearLayoutManager(this.requireContext(), RecyclerView.VERTICAL, false)
        playlistRecyclerView.adapter = adapter
    }

    private fun hideProgressBar() {
        loadingLayout.visibility = View.GONE
    }

    private fun showProgressBar() {
        loadingLayout.visibility = View.VISIBLE
    }

    private fun showError(message: String?) {
        hideProgressBar()
        errorLayout.visibility = View.VISIBLE
        errorLayout.messageTextView.text = message
    }
}
