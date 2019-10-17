package com.jvmori.topify.view.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.jvmori.topify.R
import dagger.android.support.DaggerDialogFragment
import kotlinx.android.synthetic.main.confirm_create_playlist_dialog.view.*
import java.util.*
import java.text.SimpleDateFormat


class ConfirmPlaylistCreationDialog(private val timRangeText : String = "") : DaggerDialogFragment() {

    var onConfirmListener: ConfirmPlaylistCreationListener? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.confirm_create_playlist_dialog, null)
            builder.setView(view)
                .setTitle("Create playlist")
                .setNegativeButton(
                    "CANCEL"
                ) { dialog, which -> }
                .setPositiveButton(
                    "CREATE"
                ) { dialog, which ->
                    onConfirmListener?.onConfirm(
                        view.playlistsName.text.toString(),
                        view.playlistDescription.text.toString()
                    )
                }

            val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
            view.playlistDescription.setText("Lately most listened songs. Created at: $currentDate")
            view?.playlistsName?.setText("Topify ${this.timRangeText}")
            return builder.create()
        }

        return super.onCreateDialog(savedInstanceState)
    }
}

interface ConfirmPlaylistCreationListener {
    fun onConfirm(playlistName: String, playlistDescription : String)
}