package com.example.erteklerqosqlarhmtaqmaqlar.view.taqmaq

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentTaqmaqBinding
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MainViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.view.MusicService
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaqmaqFragment : Fragment(R.layout.fragment_taqmaq) {
    private lateinit var binding: FragmentTaqmaqBinding
    private val taqmaqAdapter = TaqmaqAdapter()
    private val mainViewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTaqmaqBinding.bind(view)

        // Запускаем музыку, если не запущена
        startMusicService()

        initListeners()
        initObservers()
        setupListeners()
    }

    private fun startMusicService() {
        val intent = Intent(requireContext(), MusicService::class.java)
        requireContext().startService(intent)
    }

    override fun onResume() {
        super.onResume()
        // Проверьте состояние сервиса здесь, если нужно
    }

    override fun onPause() {
        super.onPause()
        // Проверьте состояние сервиса здесь, если нужно
    }

    private fun setupListeners() {
        binding.ivLike.setOnClickListener {
            findNavController().navigate(R.id.action_taqmaqFragment_to_favoritesTaqmaqFragment)
        }

        taqmaqAdapter.setOnLikeClickListener { itemId, isSaved ->
            MainScope().launch {
                mainViewModel.likeTaqmaq(itemId, isSaved)
                mainViewModel.getAllTaqmaq()
            }
        }

        taqmaqAdapter.setOnItemClickListener { id ->
            val bundle = Bundle()
            bundle.putInt("item_id", id)
            findNavController().navigate(
                R.id.action_taqmaqFragment_to_taqmaqInnerFragment,
                bundle
            )
        }

        binding.ivNotification.setOnClickListener {
            findNavController().navigate(R.id.action_taqmaqFragment_to_messageNewsFragment)
        }
    }

    private fun initListeners() {
        binding.taqmaqRecyclerView.adapter = taqmaqAdapter
    }

    private fun initObservers() {
        mainViewModel.taqmaqlarLiveData.observe(requireActivity()) {
            taqmaqAdapter.submitList(it)
        }

        MainScope().launch {
            mainViewModel.getAllTaqmaq()
        }
    }
}