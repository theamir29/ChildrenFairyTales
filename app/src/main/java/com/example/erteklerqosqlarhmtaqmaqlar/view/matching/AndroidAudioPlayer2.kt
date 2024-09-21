package com.example.erteklerqosqlarhmtaqmaqlar.view.matching

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.core.net.toUri
import com.example.erteklerqosqlarhmtaqmaqlar.utils.audioplayer.AudioPlayer
import java.io.File

class AndroidAudioPlayer2(
    private val context: Context
) : AudioPlayer {
    private var player: MediaPlayer? = null

    override fun play() {
        player?.start()
    }

    override fun playFile(file: File) {
        if (player == null) {
            MediaPlayer.create(context, file.toUri()).apply {
                player = this
                start()
            }
        } else {
            player?.start()
        }
    }

    override fun playRaw(audio: String) {
        MediaPlayer.create(
            context,
            Uri.parse("android.resource://com.example.erteklerqosqlarhmtaqmaqlar/raw/$audio")
        ).apply {
            player = this
            player?.start()
        }
    }

    override fun playURI(url: String) {
        if (player == null) {
            MediaPlayer.create(
                context,
                Uri.parse(url)
            ).apply {
                player = this
                player?.start()
            }
        } else {
            player?.start()
        }
    }

    override fun isPlaying(): Boolean {
        return if (player != null) {
            player?.isPlaying ?: false
        } else false
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }

    override fun resume() {
        player?.stop()
    }

    override fun seek(seek: Int) {
        player?.seekTo(seek)
    }

    override fun duration(): Int {
        return player?.duration ?: 0
    }

    override fun currentPosition(): Int {
        return player?.currentPosition ?: 0
    }

    override fun isNull(): Boolean {
        return (player == null)
    }
}