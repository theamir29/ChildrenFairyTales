package com.example.erteklerqosqlarhmtaqmaqlar.view.qosiq.viewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentQosiqSecondBinding

class SecondFragment(private val qosiq: Qosiq) : Fragment(R.layout.fragment_qosiq_second) {
    private lateinit var binding: FragmentQosiqSecondBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQosiqSecondBinding.bind(view)

//        binding.tvText.setText(R.string.example_text)
        binding.tvText.text = qosiq.text
    }
}