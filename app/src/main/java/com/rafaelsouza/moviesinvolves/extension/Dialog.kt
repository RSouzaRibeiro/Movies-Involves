package com.rafaelsouza.moviesinvolves.extension

import android.app.Dialog
import android.content.Context

import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.rafaelsouza.moviesinvolves.R


fun Dialog.showDialogSucess(context: Context, message: String): Dialog {
    val dialog = Dialog(context)
    dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog .setCancelable(false)
    dialog .setContentView(R.layout.custom_sucess_dialog)
    val body = dialog .findViewById(R.id.txtMessageSuccess) as TextView
    body.text = message
    val yesBtn = dialog .findViewById(R.id.btnOk) as Button
    yesBtn.setOnClickListener {
        dialog .dismiss()
    }

    dialog .show()

    return dialog
}

fun Dialog.showDialogError(context: Context, message: String): Dialog {
    val dialog = Dialog(context)
    dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog .setCancelable(false)
    dialog .setContentView(R.layout.custom_error_dialog)
    val body = dialog .findViewById(R.id.txtMessageError) as TextView
    body.text = message
    val yesBtn = dialog .findViewById(R.id.btnOk) as Button

    yesBtn.setOnClickListener {
        dialog .dismiss()
    }

    dialog .show()

    return dialog
}