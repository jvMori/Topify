package com.jvmori.topify.view.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.jvmori.topify.R
import dagger.android.support.DaggerDialogFragment
import kotlinx.android.synthetic.main.confirm_create_playlist_dialog.*
import kotlinx.android.synthetic.main.confirm_create_playlist_dialog.view.*


class ConfirmPlaylistCreationDialog : DaggerDialogFragment() {

    var onConfirmListener: ConfirmPlaylistCreationListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.confirm_create_playlist_dialog, null)

            builder.setView(view)
                .setTitle("Create playlist")
                .setNegativeButton(
                    "cancel"
                ) { dialog, which -> }
                .setPositiveButton(
                    "create"
                ) { dialog, which ->
                    onConfirmListener?.onConfirm(view.playlistsName.text.toString())
                }
            return builder.create()
        }

        return super.onCreateDialog(savedInstanceState)
    }
}

interface ConfirmPlaylistCreationListener {
    fun onConfirm(playlistName: String)
}