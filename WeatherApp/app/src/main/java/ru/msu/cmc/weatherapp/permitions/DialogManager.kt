package ru.msu.cmc.weatherapp.permitions

import android.app.AlertDialog
import android.content.Context

object DialogManager {
    fun locationSettingsDialog(context: Context, listener: Listener) {
        val builder = AlertDialog.Builder(context)
        val dialog = builder.create()
        dialog.setTitle("Enable location?")
        dialog.setMessage("Location disabled, do you want enable location?")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok"){ _,_ ->
            dialog.dismiss()
            listener.onOkClick()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel"){ _,_ ->
            listener.onCancelClick()
            dialog.dismiss()
        }
        dialog.show()
    }
    interface Listener {
        fun onOkClick()
        fun onCancelClick()
    }
}