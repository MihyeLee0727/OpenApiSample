package com.example.openapisample.presentation.common.view

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun Context.showSimpleAlertDlg(msg: String) {
    AlertDialog.Builder(this).setMessage(msg).show()
}

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}