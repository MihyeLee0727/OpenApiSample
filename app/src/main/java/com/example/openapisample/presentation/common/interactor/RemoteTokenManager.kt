package com.example.openapisample.presentation.common.interactor

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import com.example.openapisample.R
import com.example.openapisample.data.remote.IRemoteTokenManager

class RemoteTokenManager(
    private val context: Context
) : IRemoteTokenManager {

    companion object {
        private const val TAG = "RemoteTokenManager"
        private const val KEY_TOKEN = "KEY_TOKEN"
    }

    private var sp: SharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE)

    fun setToken(token: String) {
        sp.edit().putString(KEY_TOKEN, token).apply()
    }

    override fun getToken(): String = sp.getString(KEY_TOKEN, "").orEmpty()

    fun getBaseKey(): String {
        val key = context.getString(R.string.key).toByteArray()
        return "Basic ${Base64.encodeToString(key, Base64.NO_WRAP).orEmpty()}"
    }

}