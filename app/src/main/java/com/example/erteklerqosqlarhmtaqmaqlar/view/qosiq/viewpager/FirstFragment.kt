package com.example.erteklerqosqlarhmtaqmaqlar.view.qosiq.viewpager

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentQosiqFirstBinding

class FirstFragment(private val qosiq: Qosiq) : Fragment(R.layout.fragment_qosiq_first) {
    private lateinit var binding: FragmentQosiqFirstBinding
    private val updateHandler: Handler = Handler()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQosiqFirstBinding.bind(view)
        setVideo()
        binding.tvName.text = qosiq.name

//        binding.ivTitle.setOnPreparedListener {
//            it.isLooping = true
//            updateHandler.postDelayed(updateVideoTime, 100)
//            binding.ivTitle.start()
//        }
    }

    private fun setVideo() {
        when (qosiq.id) {
            1 -> {
                binding.ivTitle.setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName + "/raw/" + "tawlamashlar_tulkishek"))
            }

            2 -> {
                binding.ivTitle.setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName + "/raw/" + "sanamashlar_qisqa_qatar"))
            }

            3 -> {
                binding.ivTitle.setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName + "/raw/" + "sanamalar_awelemen"))
            }

            else -> {
                binding.ivTitle.setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName + "/raw/" + "janlik"))
            }
        }
        binding.ivTitle.start()
    }
}

