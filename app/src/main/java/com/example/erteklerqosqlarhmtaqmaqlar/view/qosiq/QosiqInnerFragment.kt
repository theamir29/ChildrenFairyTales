package com.example.erteklerqosqlarhmtaqmaqlar.view.qosiq

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.erteklerqosqlarhmtaqmaqlar.MainActivity
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentInnerQosiqBinding
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MainViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.utils.TAG
import com.example.erteklerqosqlarhmtaqmaqlar.utils.gone
import com.example.erteklerqosqlarhmtaqmaqlar.utils.toTime
import com.example.erteklerqosqlarhmtaqmaqlar.utils.visible
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class QosiqInnerFragment : Fragment(R.layout.fragment_inner_qosiq) {

    private lateinit var binding: FragmentInnerQosiqBinding
    private var itemId = -1
    private lateinit var mainActivity: MainActivity
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var qosiq: Qosiq
    private lateinit var mediaController: MediaController
    private lateinit var updateHandler: Handler
    private var isPlaying: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentInnerQosiqBinding.bind(view)
        try {
            mainActivity = requireActivity() as MainActivity
            mainActivity.settingsBottomNavigation(false)
            itemId = arguments?.getInt("item_id") ?: -1
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (itemId != -1) {
            initListeners()
            initObservers()
        } else {
            Toast.makeText(mainActivity, "Qátelik júz berdi!", Toast.LENGTH_SHORT).show()
            mainActivity.onBackPressed()
        }
    }

    @SuppressLint("DiscouragedApi")
    private fun initObservers() {
        mainViewModel.byIdQosiqLiveData.observe(mainActivity) {
            qosiq = it

            videoStart()

            initVideo()

            Log.d(TAG, "initObservers: n${it.audio}n")
        }

        MainScope().launch {
            mainViewModel.getByIdQosiq(itemId)
        }
    }

    private fun initVideo() {
        binding.seekBar.max = binding.videoView.duration
        updateHandler = Handler()
        updateHandler.postDelayed(updateSeekBar, 100)

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    binding.videoView.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (binding.videoView.isPlaying) {
                    binding.videoView.pause()
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (binding.videoView.isPlaying.not()) {
                    binding.videoView.start()
                }
            }
        })

        binding.videoView.setOnPreparedListener { mediaPlayer ->
            Log.d(TAG, "initVideo: isledi")
            mediaPlayer.seekTo(binding.videoView.currentPosition)
            mediaPlayer.setOnSeekCompleteListener {
                it.start()
            }

            binding.ivSkip5.setOnClickListener {
                val currentPosition = mediaPlayer.currentPosition
                val newPosition = currentPosition + 5000
                if (newPosition <= mediaPlayer.duration) {
                    mediaPlayer.seekTo(newPosition)
                } else {
                    // Handle the case when skipping forward exceeds video duration
                    Toast.makeText(requireContext(), "End of video reached", Toast.LENGTH_SHORT).show()
                }
            }

            binding.ivForward5.setOnClickListener {
                val current = mediaPlayer.currentPosition
                mediaPlayer.seekTo(current - 5000)
            }
        }
    }

    private fun videoStart() {
        when (qosiq.id) {
            1 -> {
                binding.videoView.setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName + "/raw/" + "tawlamashlar_tulkishek"))
            }

            2 -> {
                binding.videoView.setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName + "/raw/" + "sanamashlar_qisqa_qatar"))
            }

            3 -> {
                binding.videoView.setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName + "/raw/" + "sanamalar_awelemen"))
            }

            4 -> {
                binding.videoView.setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName + "/raw/" + "janlik"))
            }else -> {
                binding.videoView.setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName + "/raw/" + "yaramazan"))
            }
        }
        binding.videoView.start()
    }

    private val updateSeekBar: Runnable = object : Runnable {
        override fun run() {
            if (binding.videoView.isPlaying) {
                val currentPosition = binding.videoView.currentPosition
                binding.seekBar.max = binding.videoView.duration
                binding.seekBar.progress = currentPosition
                binding.tvCurrentTime.text = currentPosition.toTime()
                binding.tvDurationTime.text = binding.videoView.duration.toTime()
                binding.ivResume.visible()
                binding.ivPlay.gone()
            } else {
                binding.ivResume.gone()
                binding.ivPlay.visible()
            }
            updateHandler.postDelayed(this, 100)
        }
    }

    private fun initListeners() {

        binding.btnGoTest.setOnClickListener {
            findNavController().navigate(
                R.id.action_qosiqInnerFragment_to_testQosiqFragment2
            )
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.ivResume.setOnClickListener {
            binding.ivResume.gone()
            binding.ivPlay.visible()
            binding.videoView.pause()
        }

        binding.ivPlay.setOnClickListener {
            binding.ivPlay.gone()
            binding.ivResume.visible()
            binding.videoView.start()
        }

        binding.ivSkip5.setOnClickListener {
            val currentPosition = binding.videoView.currentPosition
            val newPosition = currentPosition + 5000
            if (newPosition <= binding.videoView.duration) {
                binding.videoView.seekTo(newPosition)
            } else {
                // Handle the case when skipping forward exceeds video duration
                Toast.makeText(requireContext(), "End of video reached", Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivForward5.setOnClickListener {
            val current = binding.videoView.currentPosition
            binding.videoView.seekTo(current - 5000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        updateHandler.removeCallbacks(updateSeekBar)
        (requireActivity() as MainActivity).settingsBottomNavigation(true)
    }
}