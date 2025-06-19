package com.thmanyah.common

import android.util.Log

object Logger {
    private const val TAG = "Thmanyah"


    fun d(msg: String?) {
        if (BuildConfig.DEBUG) {
            msg?.let {
                Log.d(TAG, it)
            }

        }

    }


    fun e(msg: String?) {
        if (BuildConfig.DEBUG) {
            msg?.let {
                Log.e(TAG, it)
            }

        }

    }

    fun e(error: Throwable?) {
        if (BuildConfig.DEBUG) {
            error?.let {
                it.message?.let { msg ->
                    Log.e(TAG, msg)
                }
            }
        }
    }
}