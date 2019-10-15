package com.jvmori.topify.Utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.jvmori.topify.R
import com.jvmori.topify.data.db.entity.TopTracksResponse
import com.jvmori.topify.view.fragment.FragmentCreateTop

const val topDetailsKey = "topPlaylist"
const val playlistNameKey = "playlistName"

fun navigateToDetails(item : TopTracksResponse?, playlistName: String, fragment : Fragment, actionId : Int){
    val bundle = Bundle()
    bundle.putParcelable(topDetailsKey, item)
    bundle.putString(playlistNameKey, playlistName)
    NavHostFragment.findNavController(fragment).navigate(actionId, bundle)
}
fun navigateToTopSettings(fragment: Fragment){
    NavHostFragment.findNavController(fragment).navigate(R.id.action_fragmentCreateTop_to_fragmentTopSettings2)
}
