package com.example.erteklerqosqlarhmtaqmaqlar.view.taqmaq.viewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Taqmaq
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentQosiqSecondBinding
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentTaqmaqBinding
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentTaqmaqlarSecondBinding

class SecondTaqmaqFragment(private val taqmaq: Taqmaq) : Fragment(R.layout.fragment_taqmaqlar_second) {
    private lateinit var binding: FragmentTaqmaqlarSecondBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTaqmaqlarSecondBinding.bind(view)

        binding.tvText.text= taqmaq.text
    }
}