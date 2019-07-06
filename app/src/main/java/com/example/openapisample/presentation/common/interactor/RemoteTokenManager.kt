package com.example.openapisample.presentation.common.interactor

import android.content.Context
import android.content.SharedPreferences
import com.example.openapisample.data.remote.IRemoteTokenManager

class RemoteTokenManager(
    context: Context
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

}