package com.example.erteklerqosqlarhmtaqmaqlar.view.ertek

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.erteklerqosqlarhmtaqmaqlar.MainActivity
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentPlayerBinding
import com.example.erteklerqosqlarhmtaqmaqlar.utils.gone
import com.example.erteklerqosqlarhmtaqmaqlar.utils.invisible
import com.example.erteklerqosqlarhmtaqmaqlar.utils.visible
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

class PlayerFragment : Fragment(R.layout.fragment_player) {
    private lateinit var binding: FragmentPlayerBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var videoUrl: String
    private lateinit var handler: Handler
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private lateinit var videoPlayer: PlayerView
    private lateinit var fullScreenMode: ImageView
    private lateinit var exoLock: ImageView
    private lateinit var progressBar: ProgressBar

    companion object {
        var isFullScreen = false
        var isLock = false
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayerBinding.bind(view)
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)

        binding.icBack.setOnClickListener {
            mainActivity.onBackPressed()
        }

        if (isFullScreen) {
            binding.testTablayout.gone()
        } else binding.testTablayout.visible()

        initVariables()

        initListeners()
    }

    private fun initVariables() {
        videoUrl = "https://bigzona.uz/videos/video${arguments?.getInt("item_id")}.mp4"
        handler = Handler(Looper.getMainLooper())
        videoPlayer = requireActivity().findViewById(R.id.video_player)
        progressBar = requireActivity().findViewById(R.id.progress_bar)
        fullScreenMode = requireActivity().findViewById(R.id.full_screen_mode)
        exoLock = requireActivity().findViewById(R.id.exo_lock)

        simpleExoPlayer = SimpleExoPlayer.Builder(requireActivity())
            .setSeekBackIncrementMs(10000)
            .setSeekForwardIncrementMs(10000)
            .build()

        videoPlayer.player = simpleExoPlayer

        videoPlayer.keepScreenOn = true

        val videoSource = Uri.parse(videoUrl)

        val mediaItem = MediaItem.fromUri(videoSource)

        simpleExoPlayer.setMediaItem(mediaItem)

        simpleExoPlayer.prepare()

        simpleExoPlayer.play()
    }

    private fun initListeners() {
        fullScreenMode.setOnClickListener {
            if (!isFullScreen) {
                fullScreenMode.setImageResource(
                    R.drawable.ic_fullscreen_exit
                )

                requireActivity().requestedOrientation =
                    ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            } else {
                fullScreenMode.setImageResource(
                    R.drawable.ic_fullscreen
                )


                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }

            isFullScreen = !isFullScreen
        }

        exoLock.setOnClickListener {
            if (!isLock) {
                exoLock.setImageResource(R.drawable.ic_unlock)
            } else {
                exoLock.setImageResource(R.drawable.ic_lock)
            }

            isLock = !isLock

            lockScreen(isLock)
        }

        simpleExoPlayer.addListener(object : Player.Listener {
            @Deprecated("Deprecated in Java")
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {

                if (playbackState == Player.STATE_BUFFERING) {
                    progressBar.visible()
                } else if (playbackState == Player.STATE_READY) {
                    progressBar.gone()
                }

                if (!simpleExoPlayer.playWhenReady) {

                    handler.removeCallbacks(updateProgressAction)

                } else {
                    onProgress()
                }

            }
        })
    }

    private fun onProgress() {

        val player = simpleExoPlayer
        val position: Long = player.currentPosition

        handler.removeCallbacks(updateProgressAction)

        val playBackState = player.playbackState

        if (playBackState != Player.STATE_IDLE && playBackState != Player.STATE_ENDED) {

            var delayMs: Long

            if (player.playWhenReady && playBackState == Player.STATE_READY) {
                delayMs = 10000 - position % 10000

                if (delayMs < 2000) {
                    delayMs += 10000
                } else {
                    delayMs = 10000
                }
            }

        }

    }

    private val updateProgressAction = Runnable { onProgress() }

    private fun lockScreen(lock: Boolean) {
        val controlLayout = requireActivity().findViewById<LinearLayout>(R.id.control_layout)
        val barControlLayout = requireActivity().findViewById<LinearLayout>(R.id.bar_control_layout)

        if (lock) {
            controlLayout.invisible()
            barControlLayout.invisible()
        } else {
            controlLayout.visible()
            barControlLayout.visible()
        }
    }

    override fun onStop() {
        super.onStop()

        simpleExoPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        simpleExoPlayer.release()
    }

    override fun onResume() {
        super.onResume()

        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        view?.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    Log.d("TTTT", "Exit full screen with BackPress")
                    return@OnKeyListener fullScreenMode.performClick()
                } else {
                    findNavController().popBackStack()
                    Log.d("TTTT", "Exit Fragment with BackPress")
                    return@OnKeyListener true
                }
            }
            return@OnKeyListener false
        })
    }
}