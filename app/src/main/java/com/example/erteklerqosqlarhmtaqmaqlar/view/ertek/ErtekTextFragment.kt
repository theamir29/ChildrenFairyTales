package com.example.erteklerqosqlarhmtaqmaqlar.view.ertek

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentErtekTextBinding
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MainViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.utils.TAG
import com.example.erteklerqosqlarhmtaqmaqlar.utils.setImage
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ErtekTextFragment : Fragment(R.layout.fragment_ertek_text) {
    private lateinit var binding: FragmentErtekTextBinding
    private val mainViewModel: MainViewModel by viewModel()
    private var itemId = -1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentErtekTextBinding.bind(view)
        itemId = arguments?.getInt("item_id") ?: -1
        if (itemId != -1) {
            initObservers()
        } else {
            Toast.makeText(requireActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show()
            MainScope().launch {
                delay(200)
            }
            findNavController().popBackStack()
        }


        initListeners()

    }

    private fun initObservers() {
        MainScope().launch {
            mainViewModel.getByIdErtek(itemId)
        }

        mainViewModel.byIdErtekLiveData.observe(requireActivity()) {
            Log.d(TAG, "initObservers: ${it.text}")
            Log.d(TAG, "initObservers: ${it.image}")
            binding.tvText.text = it.text
            binding.ivErtek.setImage(it.image)
        }
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}