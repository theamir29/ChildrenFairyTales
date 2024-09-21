package com.example.erteklerqosqlarhmtaqmaqlar.view.ertek

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.erteklerqosqlarhmtaqmaqlar.MainActivity
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentErtekFavoritesBinding
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MainViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.view.MusicService
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesErtekFragment : Fragment(R.layout.fragment_ertek_favorites) {

    private lateinit var binding: FragmentErtekFavoritesBinding
    private lateinit var mainActivity: MainActivity
    private val ertekAdapter = ErtekAdapter()
    private val mainViewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentErtekFavoritesBinding.bind(view)
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
        binding.ertekRecyclerView.adapter = ertekAdapter
    }

    private fun setupListeners() {
        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

        ertekAdapter.setOnLikeClickListener { itemId, isSaved ->
            MainScope().launch {
                mainViewModel.likeErtek(itemId, isSaved)
                mainViewModel.getAllErtekLike()
            }
        }
    }

    private fun initObservers() {
        mainViewModel.erteklerLikeLiveData.observe(requireActivity()) {
            Log.d("RRR", it.toString())
            ertekAdapter.submitList(it)
        }

        MainScope().launch {
            mainViewModel.getAllErtekLike()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.settingsBottomNavigation(true)
    }
}