package com.example.erteklerqosqlarhmtaqmaqlar.view.taqmaq

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.erteklerqosqlarhmtaqmaqlar.MainActivity
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentTaqmaqFavoritesBinding
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MainViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.view.MusicService
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesTaqmaqFragment : Fragment(R.layout.fragment_taqmaq_favorites) {

    private lateinit var binding: FragmentTaqmaqFavoritesBinding
    private lateinit var mainActivity: MainActivity
    private val taqmaqAdapter = TaqmaqAdapter()
    private val mainViewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTaqmaqFavoritesBinding.bind(view)
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
        binding.taqmaqRecyclerView.adapter = taqmaqAdapter
    }

    private fun setupListeners() {
        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

        taqmaqAdapter.setOnLikeClickListener { itemId, isSaved ->
            MainScope().launch {
                mainViewModel.likeTaqmaq(itemId, isSaved)
                mainViewModel.getAllTaqmaqLike()
            }
        }
    }












    private fun initObservers() {
        mainViewModel.taqmaqlarLikeLiveData.observe(requireActivity()) {
            taqmaqAdapter.submitList(it)
        }

        MainScope().launch {
            mainViewModel.getAllTaqmaqLike()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.settingsBottomNavigation(true)
    }
}