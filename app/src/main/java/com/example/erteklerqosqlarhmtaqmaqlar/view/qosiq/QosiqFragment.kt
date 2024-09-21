package com.example.erteklerqosqlarhmtaqmaqlar.view.qosiq

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentQosiqBinding
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MainViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.view.MusicService
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class QosiqFragment : Fragment(R.layout.fragment_qosiq) {
    private lateinit var binding: FragmentQosiqBinding
    private val qosiqAdapter = QosiqAdapter()
    private val mainViewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQosiqBinding.bind(view)

        // Запускаем музыку, если не запущена
        startMusicService()

        initListeners()
        setupListeners()
        initObservers()
    }

    private fun startMusicService() {
        val intent = Intent(requireContext(), MusicService::class.java)
        requireContext().startService(intent)
    }

    override fun onResume() {
        super.onResume()
        // Проверяем, что музыка воспроизводится
        // Возможно, не нужно останавливать сервис здесь
    }

    override fun onPause() {
        super.onPause()
        // Возможно, не нужно запускать сервис здесь
    }

    private fun setupListeners() {
        binding.ivLike.setOnClickListener {
            findNavController().navigate(R.id.action_qosiqFragment_to_favoritesQosiqFragment)
        }

        binding.ivNotification.setOnClickListener {
            findNavController().navigate(R.id.action_qosiqFragment_to_messageNewsFragment)
        }
    }

    private fun initListeners() {
        binding.qosiqRecyclerView.adapter = qosiqAdapter

        qosiqAdapter.setOnLikeClickListener { itemId, isSaved ->
            MainScope().launch {
                mainViewModel.likeQosiq(itemId, isSaved)
                mainViewModel.getAllQosiq()
            }
        }

        qosiqAdapter.setOnItemClickListener { id, position ->
            val bundle = Bundle()
            bundle.putInt("item_id", id)
            findNavController().navigate(R.id.action_qosiqFragment_to_qosiqInnerFragment, bundle)
        }
    }

    private fun initObservers() {
        mainViewModel.qosiqlarLiveData.observe(requireActivity()) {
            qosiqAdapter.submitList(it)
        }

        MainScope().launch {
            mainViewModel.getAllQosiq()
        }
    }
}