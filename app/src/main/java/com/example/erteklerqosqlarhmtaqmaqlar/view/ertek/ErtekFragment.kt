package com.example.erteklerqosqlarhmtaqmaqlar.view.ertek

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentErtekBinding
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MainViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.network.NetworkViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.utils.getUserToken
import com.example.erteklerqosqlarhmtaqmaqlar.utils.toRequestData
import com.example.erteklerqosqlarhmtaqmaqlar.view.MusicService
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat

class ErtekFragment : Fragment(R.layout.fragment_ertek) {
    private lateinit var binding: FragmentErtekBinding
    private val ertekAdapter = ErtekAdapter()
    private val mainViewModel: MainViewModel by viewModel()
    private val networkViewModel: NetworkViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentErtekBinding.bind(view)

        initListeners()

        initObservers()

        setupListeners()

        // Запускаем музыку, если не запущена
        val intent = Intent(requireContext(), MusicService::class.java)
        requireContext().startService(intent)


//        binding.tvTitle.setOnClickListener {
//            findNavController().navigate(R.id.action_ertekFragment_to_videoFragment)
//        }

    }



    private fun setupListeners() {
        ertekAdapter.setOnLikeClickListener { itemId, isSaved ->
            MainScope().launch {
                mainViewModel.likeErtek(itemId, isSaved)
                mainViewModel.getAllErtek()
            }
        }

        ertekAdapter.setOnItemClickListener { ertek ->
            val bundle = Bundle()
            bundle.putInt("item_id", ertek.id)
            findNavController().navigate(R.id.action_ertekFragment_to_innerErtekFragment, bundle)
//            findNavController().navigate(R.id.action_ertekFragment_to_videoFragment, bundle)
        }

        binding.ivNotification.setOnClickListener {
            findNavController().navigate(R.id.action_ertekFragment_to_messageNewsFragment)
        }
    }

    private fun initObservers() {
        mainViewModel.erteklerLiveData.observe(requireActivity()) {
            ertekAdapter.submitList(it)
        }

        MainScope().launch {
            mainViewModel.getAllErtek()
        }

        networkViewModel.notificationResponseLiveData.observe(requireActivity()) {
            Toast.makeText(requireActivity(), it.success.toString(), Toast.LENGTH_SHORT).show()
        }

        networkViewModel.messageLiveData.observe(requireActivity()) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }

        networkViewModel.errorLiveData.observe(requireActivity()) {
            Log.d("TAG", "initObservers: ${it.message}")
            it.printStackTrace()
            Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun initListeners() {
        binding.ertekRecyclerView.adapter = ertekAdapter

        binding.ivLike.setOnClickListener {
            findNavController().navigate(R.id.action_ertekFragment_to_favoritesErtekFragment)
        }

        binding.tvTitle.setOnClickListener {
            MainScope().launch {
                val time = SimpleDateFormat("HH:mm").format(System.currentTimeMillis())
                networkViewModel.sendNotification(
                    toRequestData(
                        "Ertekler, qosıqlar hám ertekler",
                        "Sizge jańa xabar keldi($time)"
                    ),
                    getUserToken(requireActivity())
                )
            }
        }

//        binding.tvTitle.setOnClickListener {
//            val mediaPlayer = MediaPlayer.create(requireActivity(), R.raw.audio)
//            mediaPlayer.start()
//        }
    }


}