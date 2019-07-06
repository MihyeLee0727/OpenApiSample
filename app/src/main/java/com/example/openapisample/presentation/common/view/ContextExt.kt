package com.example.openapisample.presentation.common.view

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun Context.showSimpleAlertDlg(msg: String, callback: (() -> Unit)? = null) {
    AlertDialog.Builder(this).setMessage(msg).setPositiveButton("확인") { _, _ ->
        callback?.invoke()
    }.show()
}

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}