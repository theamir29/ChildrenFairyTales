package com.example.erteklerqosqlarhmtaqmaqlar.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.erteklerqosqlarhmtaqmaqlar.MainActivity
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentNewsMessageBinding
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.network.GetFeedbacksViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.utils.getUserToken
import com.example.erteklerqosqlarhmtaqmaqlar.utils.snackBar
import com.example.erteklerqosqlarhmtaqmaqlar.view.ertek.GetFeedbackAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageNewsFragment : Fragment(R.layout.fragment_news_message) {

    private lateinit var binding: FragmentNewsMessageBinding
    private lateinit var mainActivity: MainActivity
    private val viewModel by viewModel<GetFeedbacksViewModel>()

    private val adapter = GetFeedbackAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)

        binding = FragmentNewsMessageBinding.bind(view)

        startMusicService()

        binding.icBack.setOnClickListener {
            mainActivity.onBackPressed()
        }

        Log.d("TTT", getUserToken(requireActivity()))

        viewModel.getFeedbacks(
            device_key = getUserToken(requireActivity())
        )

        binding.messageRecyclerView.adapter = adapter
        initObservers()
    }


    private fun startMusicService() {
        val intent = Intent(requireContext(), MusicService::class.java)
        requireContext().startService(intent)
    }

    private fun initObservers() {
        viewModel.getFeedbackResult.onEach { result ->
            result.onSuccess {
                if (it.data.feedbacks.isNotEmpty()) {
                    adapter.submitList(it.data.feedbacks)
                } else {
                    binding.icNullImage.visibility = View.VISIBLE
                }
            }

            result.onFailure {
                snackBar(it.localizedMessage)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDetach() {
        super.onDetach()
        mainActivity.settingsBottomNavigation(true)
    }
}