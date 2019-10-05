package com.jvmori.topify.view.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.jvmori.topify.Utils.ImageLoader
import com.jvmori.topify.Utils.SessionManager
import com.jvmori.topify.Utils.topDetailsKey
import com.jvmori.topify.data.Resource
import com.jvmori.topify.data.db.entity.TopTracksResponse
import com.jvmori.topify.data.response.playlist.AddTracks
import com.jvmori.topify.data.response.top.Track
import com.jvmori.topify.view.activity.AuthResource
import com.jvmori.topify.view.adapters.createdPlaylist.TrackItem
import com.jvmori.topify.view.viewmodel.CreateTopViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_top_details.*
import javax.inject.Inject
import dagger.android.support.DaggerAppCompatActivity


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
    private val tracksUris = mutableListOf<String>()

    private lateinit var playlistId : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        topViewModel = ViewModelProviders.of(this, factory).get(CreateTopViewModel::class.java)
        createPlaylist()
        topViewModel.addTracksSnapshot().observe(this, Observer {
           when(it.status){
               Resource.Status.SUCCESS -> {
                   Log.i("TOPIFY", it.data?.snapshot_id)
                   showPlaylistImage(playlistId)
                   hideProgressBar()
               }
           }
        })

        return inflater.inflate(com.jvmori.topify.R.layout.fragment_top_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val playlistResponse = getTopTracksResponse()
        playlistResponse?.let {
            createUris(it)
            showCreatedPlaylist(it.tracks)
        }
    }

    private fun getTopTracksResponse(): TopTracksResponse? {
        return (arguments?.getParcelable(topDetailsKey)) as TopTracksResponse?
    }

    private fun createUris(data: TopTracksResponse?) {
        data?.tracks?.forEach { item ->
            tracksUris.add(item.uri)
        }
    }

    private fun createPlaylist() {
        sessionManager.getUser().observe(this, Observer { user ->
            when (user.status) {
                AuthResource.AuthStatus.AUTHENTICATED -> {
                    topViewModel.createTopTracksPlaylist(user.data?.id!!, "Topify top 50")
                }
            }
        })
        topViewModel.topTracksPlaylist().observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> showProgressBar()
                Resource.Status.SUCCESS -> {
                    hideProgressBar()
                    topViewModel.addTracksToPlaylist(it.data?.id!!, AddTracks(uris = tracksUris))
                    playlistId = it.data.id
                    toolbarPlaylistName.text = it.data.name
                }
                Resource.Status.ERROR -> error(it.message)
            }
        })
    }

    private fun hideProgressBar(){
        loadingLayout.visibility = View.GONE
    }

    private fun showProgressBar() {
       //loadingLayout.visibility = View.VISIBLE
    }

    private fun showCreatedPlaylist(tracks: List<Track>) {
        val adapter = GroupAdapter<ViewHolder>()
        tracks.forEach {
            adapter.add(TrackItem(it))
        }
        playlistRecyclerView.layoutManager = LinearLayoutManager(this.requireContext(), RecyclerView.VERTICAL, false)
        playlistRecyclerView.adapter = adapter
    }

    private fun showPlaylistImage(playlistId: String) {
        topViewModel.fetchPlaylistCoverImage(playlistId)
        topViewModel.getPlaylistCoverImage().observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> imageLoader.loadImage(
                    it.data?.let{
                       list -> list[0].url
                    },
                    playlistsCoverImg
                )
                Resource.Status.ERROR -> Log.i("Topify", "something went wrong!")
            }
        })
    }

    private fun error(message: String?) {
        hideProgressBar()
        Log.i("TOPIFY", message)
    }
}
