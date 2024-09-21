package com.example.erteklerqosqlarhmtaqmaqlar.view.ertek

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.erteklerqosqlarhmtaqmaqlar.MainActivity
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.local.Constants
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentVideoBinding
import com.example.erteklerqosqlarhmtaqmaqlar.utils.gone
import com.example.erteklerqosqlarhmtaqmaqlar.utils.visible


class VideoFragment : Fragment(R.layout.fragment_video) {
    private lateinit var binding: FragmentVideoBinding
    private lateinit var videoUrl: String
    private val updateHandler: Handler = Handler()
    private val adapter = RecoPicAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVideoBinding.bind(view)
        (requireActivity() as MainActivity).settingsBottomNavigation(false)
        binding.progressBar.bringToFront()
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        val itemId = arguments?.getInt("item_id", -1) ?: -1
        if (itemId == -1) {
            Toast.makeText(requireActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        } else {
            videoUrl = "https://bigzona.uz/videos/video${itemId}.mp4"
            adapter.submitList(Constants.recomendations[itemId - 1])
            initVideo()
            binding.progressBar.bringToFront()
        }
    }

    private fun initVideo() {
        binding.videoView.setVideoURI(Uri.parse(videoUrl))
        binding.progressBar.bringToFront()
        binding.videoView.requestFocus()

        binding.videoView.setOnPreparedListener {
            binding.progressBar.gone()
            it.isLooping = true
            updateHandler.postDelayed(updateVideoTime, 100)
            binding.videoView.start()
        }

        binding.videoView.setOnCompletionListener {
            Toast.makeText(requireActivity(), "Completed", Toast.LENGTH_SHORT).show()
        }

        binding.videoView.setOnErrorListener { _, _, _ ->
            Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show()
            binding.progressBar.gone()
            false
        }

        binding.progressBar.visible()
        binding.progressBar.bringToFront()
        binding.videoView.setOnInfoListener { _, what, _ ->
            if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
                binding.progressBar.visible()
            } else if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
                binding.progressBar.gone()
            }
            true
        }

        binding.seekBar.max = binding.videoView.duration

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (binding.videoView.isPlaying) {
                    updateHandler.postDelayed(updateVideoTime, 100)
                    binding.videoView.pause()
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                val pro = seekBar?.progress
                binding.videoView.seekTo(pro!!)
                if (binding.videoView.isPlaying.not()) {
                    binding.videoView.seekTo(pro)
                    updateHandler.postDelayed(updateVideoTime, 100)
                    binding.videoView.start()
                }
            }
        })

        binding.ivPlay.setOnClickListener {
            binding.videoView.start()
            updateHandler.postDelayed(updateVideoTime, 100)
            binding.ivPlay.gone()
            binding.ivResume.visible()
        }

        binding.ivResume.setOnClickListener {
            binding.videoView.pause()
            updateHandler.postDelayed(updateVideoTime, 100)
            binding.ivResume.gone()
            binding.ivPlay.visible()
        }

        binding.ivSkip5.setOnClickListener {
            binding.videoView.seekTo(binding.videoView.currentPosition + 5000)
            updateHandler.postDelayed(updateVideoTime, 100)
        }

        binding.ivForward5.setOnClickListener {
            binding.videoView.seekTo(binding.videoView.currentPosition - 5000)
            updateHandler.postDelayed(updateVideoTime, 100)
        }
    }

    private fun initListeners() {
        binding.rvRecomendation.adapter = adapter

        adapter.setOnItemClickListener {
            updateHandler.postDelayed(updateVideoTime, 100)
            binding.videoView.seekTo(it.time * 1000)
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private val updateVideoTime: Runnable = object : Runnable {
        override fun run() {
            if (binding.videoView.isPlaying) {
                binding.ivResume.visible()
                binding.ivPlay.gone()
                val currentPosition: Long = binding.videoView.currentPosition.toLong()
                binding.seekBar.progress = currentPosition.toInt()
                binding.seekBar.max = binding.videoView.duration
                binding.tvCurrentTime.text = currentPosition.toInt().toClock()
                binding.tvDurationTime.text = binding.videoView.duration.toClock()
                updateHandler.postDelayed(this, 100)
            } else {
                binding.ivResume.gone()
                binding.ivPlay.visible()
            }
        }
    }

    private fun Int.toClock(): String =
        "00${this / 1000 / 60}".takeLast(2) + ":" + "00${this / 1000 % 60}".takeLast(2)
}