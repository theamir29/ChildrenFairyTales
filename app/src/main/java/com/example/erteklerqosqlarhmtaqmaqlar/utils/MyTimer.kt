package com.example.erteklerqosqlarhmtaqmaqlar.utils

import android.os.Handler
import android.os.Looper


class MyTimer {
    private var handler: Handler? = null
    private var runnable: Runnable? = null

    fun start(callback: () -> Unit) {
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {
                callback()
                handler?.postDelayed(this, 100)
            }
        }
        handler?.postDelayed(runnable!!, 100)
    }

    fun stop() {
        handler?.removeCallbacks(runnable!!)
    }
}