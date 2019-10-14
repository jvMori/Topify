package com.jvmori.topify.Utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.jvmori.topify.R
import com.jvmori.topify.data.db.entity.TopTracksResponse

const val topDetailsKey = "topPlaylist"

fun navigateToDetails(item : TopTracksResponse?, fragment : Fragment, actionId : Int){
    val bundle = Bundle()
    bundle.putParcelable(topDetailsKey, item)
    NavHostFragment.findNavController(fragment).navigate(actionId, bundle)
}
fun navigateToTopSettings(fragment: Fragment){
    NavHostFragment.findNavController(fragment).navigate(R.id.action_fragmentCreateTop_to_fragmentTopSettings2)
}

fun showConfirmationDialog(fragment: Fragment){
    NavHostFragment.findNavController(fragment).navigate(R.id.action_fragmentCreateTop_to_confirmPlaylistCreationDialog)
}
