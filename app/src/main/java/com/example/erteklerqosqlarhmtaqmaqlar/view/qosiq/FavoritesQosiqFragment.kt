package com.example.erteklerqosqlarhmtaqmaqlar.view.qosiq

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.erteklerqosqlarhmtaqmaqlar.MainActivity
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentQosiqFavoritesBinding
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MainViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.view.MusicService
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesQosiqFragment : Fragment(R.layout.fragment_qosiq_favorites) {

    private lateinit var binding: FragmentQosiqFavoritesBinding
    private lateinit var mainActivity: MainActivity
    private val qosiqAdapter = QosiqAdapter()
    private val mainViewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQosiqFavoritesBinding.bind(view)
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)

        startMusicService()

        initListeners()
        setupListeners()
        initObservers()

    }

    private fun startMusicService() {
        val intent = Intent(requireContext(), MusicService::class.java)
        requireContext().startService(intent)
    }


    private fun initListeners() {
        binding.qosiqRecyclerView.adapter = qosiqAdapter
    }

    private fun setupListeners() {
        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

        qosiqAdapter.setOnLikeClickListener { itemId, isSaved ->
            MainScope().launch {
                mainViewModel.likeQosiq(itemId, isSaved)
                mainViewModel.getAllQosiqLike()
            }
        }
    }

    private fun initObservers() {
        mainViewModel.qosiqlarLikeLiveData.observe(requireActivity()) {
            qosiqAdapter.submitList(it)
        }

        MainScope().launch {
            mainViewModel.getAllQosiqLike()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.settingsBottomNavigation(true)
    }
}