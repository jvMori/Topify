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
import com.jvmori.topify.Utils.ImageLoader
import com.jvmori.topify.Utils.navigateToDetails
import com.jvmori.topify.Utils.navigateToTopSettings
import com.jvmori.topify.data.Resource
import com.jvmori.topify.data.response.top.TimeRange
import com.jvmori.topify.data.response.top.TopCategory
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.data.response.top.Track
import com.jvmori.topify.view.adapters.TopTracksAdapter
import com.jvmori.topify.view.viewmodel.CreateTopViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_create_top.*
import kotlinx.android.synthetic.main.top_toolbar.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentCreateTop : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var imageLoader: ImageLoader

    private var topViewModel: CreateTopViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topViewModel = activity?.let {
            ViewModelProviders.of(it, factory).get(CreateTopViewModel::class.java)
        }
        topViewModel?.fetchTopParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_create_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topViewModel?.getTopParam()?.observe(this, Observer {
            when (it.topCategory) {
                TopCategory.TRACKS -> displayTopTracks(it)
                TopCategory.ARTISTS -> displayTopArtists(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_actions, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_controls -> {
            displaySettings()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }


    private fun displaySettings() {
        Log.i("TOPIFY", "clicked")
        navigateToTopSettings(this)
    }

    private fun displayTopArtists(topParam: TopParam) {
        topViewModel?.fetchTopArtists(topParam)
        topViewModel?.topArtists()?.observe(this, Observer { topArtists ->
            when (topArtists.status) {
                Resource.Status.LOADING -> showLoading()
                Resource.Status.SUCCESS -> {
                    // create_btn.visibility = View.GONE
                    Log.i("TOPIFY", topArtists.toString())
                }
                Resource.Status.ERROR -> error(topArtists.message)
            }
        })
    }

    @SuppressLint("RestrictedApi")
    private fun displayTopTracks(topParam: TopParam) {
        topViewModel?.fetchTopTracks(topParam)
        topViewModel?.topTracks()?.observe(this, Observer { topTracks ->
            when (topTracks.status) {
                Resource.Status.LOADING -> showLoading()
                Resource.Status.SUCCESS -> {
                    create_btn.visibility = View.VISIBLE
                    createTopTracksAdapter(topTracks.data?.tracks)
                    create_btn.setOnClickListener {
                        navigateToDetails(
                            topTracks.data,
                            this,
                            R.id.action_fragmentCreateTop_to_fragmentTopDetails
                        )
                    }
                }
                Resource.Status.ERROR -> error(topTracks.message)
            }
        })
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
