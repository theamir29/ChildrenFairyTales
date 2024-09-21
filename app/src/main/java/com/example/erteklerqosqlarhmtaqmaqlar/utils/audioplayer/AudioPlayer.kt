package com.example.erteklerqosqlarhmtaqmaqlar.utils.audioplayer

import java.io.File

interface AudioPlayer {
    fun play()
    fun playFile(file: File)

    fun playRaw(audio: String)

    fun playURI(url: String)
    fun stop()

    fun resume()

    fun seek(seek: Int)


    fun isPlaying(): Boolean

    fun duration(): Int

    fun currentPosition(): Int

    fun isNull(): Boolean
}