package com.jvmori.topify.view.dialog

import android.app.Dialog
import android.os.Bundle
import dagger.android.support.DaggerDialogFragment
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.jvmori.topify.R
import kotlinx.android.synthetic.main.confirm_create_playlist_dialog.*


class ConfirmPlaylistCreationDialog : DaggerDialogFragment() {

    var onConfirmListener: ConfirmPlaylistCreationListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        context?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = activity!!.layoutInflater
            val view = inflater.inflate(R.layout.confirm_create_playlist_dialog, null)

            builder.setView(view)
                .setTitle("Create playlist")
                .setNegativeButton(
                    "cancel"
                ) { dialog, which -> }
                .setPositiveButton(
                    "create"
                ) { dialog, which -> onConfirmListener?.onConfirm(playlistsName.text.toString()) }
            return builder.create()
        }

        return super.onCreateDialog(savedInstanceState)
    }
}

interface ConfirmPlaylistCreationListener {
    fun onConfirm(playlistName: String)
}