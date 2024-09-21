package com.example.erteklerqosqlarhmtaqmaqlar.view.ertek

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
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Ertek
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentAnotherQosiqBinding
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentInnerQosiqBinding
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MainViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.utils.TAG
import com.example.erteklerqosqlarhmtaqmaqlar.utils.gone
import com.example.erteklerqosqlarhmtaqmaqlar.utils.toTime
import com.example.erteklerqosqlarhmtaqmaqlar.utils.visible
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class QosiqAnotherFragment : Fragment(R.layout.fragment_another_qosiq) {

    private lateinit var binding: FragmentAnotherQosiqBinding
    private var itemId = -1
    private lateinit var mainActivity: MainActivity
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var qosiq: Ertek
    private lateinit var mediaController: MediaController
    private lateinit var updateHandler: Handler
    private var isPlaying: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAnotherQosiqBinding.bind(view)
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
        mainViewModel.byIdErtekLiveData.observe(mainActivity) {
            qosiq = it

            videoStart()

            initVideo()

            Log.d(TAG, "initObservers: n${it.image}n")
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
                binding.videoView.setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName + "/raw/" + "tawlamashlar_tulkishek"))
            }

            3 -> {
                binding.videoView.setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName + "/raw/" + "tawlamashlar_tulkishek"))
            }

            else -> {
                binding.videoView.setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName + "/raw/" + "tawlamashlar_tulkishek"))
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

//        binding.ivSkip5.setOnClickListener {
//            val currentPosition = binding.videoView.currentPosition
//            val newPosition = currentPosition + 5000
//            if (newPosition <= binding.videoView.duration) {
//                binding.videoView.seekTo(newPosition)
//            } else {
//                // Handle the case when skipping forward exceeds video duration
//                Toast.makeText(requireContext(), "End of video reached", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        binding.ivForward5.setOnClickListener {
//            val current = binding.videoView.currentPosition
//            binding.videoView.seekTo(current - 5000)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        updateHandler.removeCallbacks(updateSeekBar)
        (requireActivity() as MainActivity).settingsBottomNavigation(true)
    }
}