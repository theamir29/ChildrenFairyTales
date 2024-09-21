package com.example.erteklerqosqlarhmtaqmaqlar.view.ertek

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.erteklerqosqlarhmtaqmaqlar.MainActivity
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentInnerErtekBinding
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MainViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.utils.TAG
import com.example.erteklerqosqlarhmtaqmaqlar.utils.setImage
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class InnerErtekFragment : Fragment(R.layout.fragment_inner_ertek) {
    private lateinit var binding: FragmentInnerErtekBinding
    private var itemId = -1
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var mainActivity: MainActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInnerErtekBinding.bind(view)
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        itemId = arguments?.getInt("item_id") ?: -1

        initListeners()

        initObservers()

    }

    private fun initObservers() {
        mainViewModel.byIdErtekLiveData.observe(requireActivity()) {
//            Log.d(TAG, "initObservers: ${it.image}")
//            binding.ivErtek.setImage("ertek_example")
//            binding.ivErtek.setImage(it.image)
            if (it != null) {
                binding.ivErtek.setImage(it.image)
                binding.tvErtekName.text = it.name
            }
        }

        MainScope().launch {
            mainViewModel.getByIdErtek(itemId)
            Log.d(TAG, "initObservers: $itemId")
        }
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.watchVideo.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("item_id", itemId)
//            findNavController().navigate(R.id.action_innerErtekFragment_to_playerFragment, bundle)
            findNavController().navigate(R.id.action_innerErtekFragment_to_videoFragment, bundle)
        }

        binding.watchText.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("item_id", itemId)
            findNavController().navigate(
                R.id.action_innerErtekFragment_to_ertekTextFragment,
                bundle
            )
        }

        binding.btnTest.setOnClickListener {
            findNavController().navigate(R.id.action_innerErtekFragment_to_testFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mainActivity.isInitialized) {
            mainActivity.settingsBottomNavigation(true)
        }
    }
}