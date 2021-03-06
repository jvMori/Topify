package com.jvmori.topify.view.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jvmori.topify.R
import com.jvmori.topify.Utils.*
import com.jvmori.topify.data.Resource
import com.jvmori.topify.data.db.entity.TopArtistsResponse
import com.jvmori.topify.data.db.entity.TopTracksResponse
import com.jvmori.topify.data.response.top.*
import com.jvmori.topify.view.adapters.TopTracksAdapter
import com.jvmori.topify.view.adapters.top.ArtistViewItem
import com.jvmori.topify.view.dialog.ConfirmPlaylistCreationDialog
import com.jvmori.topify.view.dialog.ConfirmPlaylistCreationListener
import com.jvmori.topify.view.viewmodel.CreateTopViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_create_top.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentCreateTop : DaggerFragment(), ConfirmPlaylistCreationListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var imageLoader: ImageLoader

    private var topViewModel: CreateTopViewModel? = null
    private var topTracksResponse: TopTracksResponse? = null
    private lateinit var timeRangeText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topViewModel = activity?.let {
            ViewModelProviders.of(it, factory).get(CreateTopViewModel::class.java)
        }
        topViewModel?.fetchTopParams()
    }

    override fun onResume() {
        super.onResume()
        topViewModel?.getTopParam()?.observe(this, Observer {
            timeRangeText = setTimeRangeText(it.timeRange)
            displayArtistOrTracksList(it)
            setActionBarTitle(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_create_top, container, false)
    }

    private fun setActionBarTitle(it: TopParam) {
        val navController = Navigation.findNavController(this.requireView())
        navController.currentDestination?.label =
            "Top ${it.topCategory.toString().toLowerCase()} $timeRangeText"
        Log.i("TOPIFY", "top")
    }

    private fun setTimeRangeText(timeRange: String) : String{
        return when (timeRange) {
            TimeRange().shortTerm -> "last week"
            TimeRange().mediumTerm -> "last month"
            TimeRange().longTerm -> "last year"
            else -> ""
        }
    }


    private fun displayArtistOrTracksList(it: TopParam) {
        when (it.topCategory) {
            TopCategory.TRACKS -> displayTopTracks(it)
            TopCategory.ARTISTS -> displayTopArtists(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_actions, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_controls -> {
            navigateToTopSettings(this)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun displayTopArtists(topParam: TopParam) {
        topViewModel?.fetchTopArtists(topParam)
        topViewModel?.topArtists()?.observe(this, Observer { topArtists ->
            when (topArtists.status) {
                Resource.Status.LOADING -> showLoading()
                Resource.Status.SUCCESS -> artistsSuccess(topArtists)
                Resource.Status.ERROR -> error(topArtists.message)
            }
        })
    }

    @SuppressLint("RestrictedApi")
    private fun artistsSuccess(topArtists: Resource<TopArtistsResponse>) {
        create_btn.visibility = View.GONE
        createArtistsAdapter(topArtists)
    }

    private fun createArtistsAdapter(topArtists: Resource<TopArtistsResponse>) {
        val artistsAdapter = GroupAdapter<ViewHolder>()
        topArtists.data?.artists?.let { artists ->
            artists.forEach {
                artistsAdapter.add(ArtistViewItem(imageLoader, it) {artistItem ->
                    navigateToArtistDetails(artistItem, this, R.id.action_fragmentCreateTop_to_fragmentArtistDetails)
                })
            }
        }
        topRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = artistsAdapter
        }
    }

    private fun displayTopTracks(topParam: TopParam) {
        topViewModel?.fetchTopTracks(topParam)
        topViewModel?.topTracks()?.observe(this, Observer { topTracks ->
            when (topTracks.status) {
                Resource.Status.LOADING -> showLoading()
                Resource.Status.SUCCESS -> topTracksSuccess(topTracks)
                Resource.Status.ERROR -> error(topTracks.message)
            }
        })
    }

    @SuppressLint("RestrictedApi")
    private fun topTracksSuccess(topTracks: Resource<TopTracksResponse>) {
        create_btn.visibility = View.VISIBLE
        createTopTracksAdapter(topTracks.data?.tracks)
        create_btn.setOnClickListener {
            topTracksResponse = topTracks.data
            showConfirmationDialog()
        }
    }

    private fun showConfirmationDialog() {
        val dialog = ConfirmPlaylistCreationDialog(timeRangeText)
        dialog.onConfirmListener = this
        fragmentManager?.let {
            dialog.show(it, "ConfirmPlaylistCreationDialog")
        }
    }

    override fun onConfirm(playlistName: String, playlistDescription : String) {
        navigateToDetails(
            topTracksResponse,
            playlistName,
            playlistDescription,
            this,
            R.id.action_fragmentCreateTop_to_fragmentTopDetails
        )
    }

    private fun createTopTracksAdapter(tracks: List<Track>?) {
        val topTracksAdapter = TopTracksAdapter(tracks, imageLoader)
        topRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = topTracksAdapter
        }
    }

    private fun showLoading() {

    }

    private fun error(message: String?) {
        Log.i("TOPIFY", message)
    }

}
