package com.example.erteklerqosqlarhmtaqmaqlar.view.matching

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.erteklerqosqlarhmtaqmaqlar.MainActivity
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentMatchingBinding
import com.example.erteklerqosqlarhmtaqmaqlar.utils.audioplayer.AndroidAudioPlayer
import com.example.erteklerqosqlarhmtaqmaqlar.utils.gone
import com.example.erteklerqosqlarhmtaqmaqlar.utils.visible

class MatchingFragment : Fragment(R.layout.fragment_matching) {

    private lateinit var binding: FragmentMatchingBinding
    private val isSelected1LiveData = MutableLiveData<Boolean>()
    private val isSelected2LiveData = MutableLiveData<Boolean>()
    private lateinit var amirAudio: AndroidAudioPlayer2
    private lateinit var mediaPlayer: MediaPlayer



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMatchingBinding.bind(view)

        setData(binding.findSimilarRv1, binding.findSimilarRv2)


        amirAudio = AndroidAudioPlayer2(this@MatchingFragment.requireContext())

        mediaPlayer = MediaPlayer.create(requireActivity(), R.raw.ertek_qaharmaninin_jubin_tabin)
        mediaPlayer.start() // Запуск аудио



        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setData(
        rv1: RecyclerView, rv2: RecyclerView
    ) {
        val adapter1 =
            FindSimilarAdapter(
                true,
                viewLifecycleOwner,
                isSelected1LiveData,
            )
        rv1.adapter = adapter1


        val randomSaykes = when ((1..10).random()) {
            1 -> Constants.saykes
            2 -> Constants.saykes2
            3 -> Constants.saykes3
            4 -> Constants.saykes4
            5 -> Constants.saykes5
            6 -> Constants.saykes6
            7 -> Constants.saykes7
            8 -> Constants.saykes8
            9 -> Constants.saykes9
            else -> Constants.saykes10
        }

        adapter1.submitList(randomSaykes[(requireActivity() as MainActivity).bookID].shuffled())

        val adapter2 =
            FindSimilarAdapter(
                false,
                viewLifecycleOwner,
                isSelected2LiveData,
            )
        rv2.adapter = adapter2
        adapter2.submitList(randomSaykes[(requireActivity() as MainActivity).bookID].shuffled())

        isSelected1LiveData.value = false
        isSelected2LiveData.value = false

        var first = true
        var firstSelected: Pair<saykesModel, Int>? = null
        var secondSelected: Pair<saykesModel, Int>? = null

        adapter1.setOnItemClickListener {
            if (first) {
                firstSelected = Pair(it, it.variant1)
                isSelected1LiveData.value = true
                first = first.not()
            } else {
                secondSelected = Pair(it, it.variant1)
                if (firstSelected?.first == secondSelected?.first && firstSelected?.second != secondSelected?.second) {
                    binding.trueAnswers.text = "${binding.trueAnswers.text.toString().toInt() + 1}"
                    adapter2.removeItem(it)
                    adapter1.removeItem(it)
                    isSelected1LiveData.value = false
                    isSelected2LiveData.value = false

                    audioPlay("correct_audio")

                } else if (firstSelected?.first != secondSelected?.first) {
                    vibrateDevice(300)
                    answer(false)
                    binding.wrongAnswers.text =
                        "${binding.wrongAnswers.text.toString().toInt() + 1}"
                    isSelected1LiveData.value = false
                    isSelected2LiveData.value = false
                }
                first = first.not()

                if ((adapter1.itemCount == 1 || adapter2.itemCount == 1) && first && firstSelected?.second != secondSelected?.second) {
                    val context: Context = requireContext()

                    if (isNetworkAvailable(context)) {
                        findNavController().navigate(
                            MatchingFragmentDirections.actionMatchingFragmentToQuestionFragment()
                        )
                    } else {
                        Toast.makeText(context, "Sizde Internet qosılmaǵan", Toast.LENGTH_SHORT)
                            .show()
                    }
                    answer(true)
                  /*  Toast.makeText(
                        requireActivity(), "You win!", Toast.LENGTH_SHORT
                    ).show()*/
                }
                Log.d("TAG", "setData: ${adapter1.itemCount}, ${adapter2.itemCount}")
            }
        }

        adapter2.setOnItemClickListener {
            if (first) {
                firstSelected = Pair(it, it.variant2)
                isSelected2LiveData.value = true
                first = first.not()
            } else {
                secondSelected = Pair(it, it.variant2)
                if (firstSelected?.first == secondSelected?.first && firstSelected?.second != secondSelected?.second) {
                    binding.trueAnswers.text = "${binding.trueAnswers.text.toString().toInt() + 1}"
                    adapter2.removeItem(it)
                    adapter1.removeItem(it)
                    isSelected1LiveData.value = false
                    isSelected2LiveData.value = false

                    audioPlay("correct_audio")


                } else if (firstSelected?.second != secondSelected?.second) {
                    vibrateDevice(300)
                    answer(false)
                    binding.wrongAnswers.text =
                        "${binding.wrongAnswers.text.toString().toInt() + 1}"
                    isSelected1LiveData.value = false
                    isSelected2LiveData.value = false
                }
                first = first.not()
                if ((adapter1.itemCount == 1 || adapter2.itemCount == 1) && first && firstSelected?.second != secondSelected?.second) {
                    val context: Context = requireContext()

                    if (isNetworkAvailable(context)) {
                        findNavController().navigate(
                            MatchingFragmentDirections.actionMatchingFragmentToQuestionFragment()
                        )
                    } else {
                        Toast.makeText(context, "Sizde Internet qosılmaǵan", Toast.LENGTH_SHORT)
                            .show()
                    }
                    answer(true)
                  /*  Toast.makeText(
                        requireActivity(), "You win!", Toast.LENGTH_SHORT
                    ).show()*/
                }
                Log.d("TAG", "setData: ${adapter1.itemCount}, ${adapter2.itemCount}")
            }
        }
    }

    @SuppressLint("ServiceCast")
    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            networkCapabilities != null && (networkCapabilities.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI
            ) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }

    private fun answer(result: Boolean) {
        if (result) {
            binding.viwBlur.visible()
            binding.lottieAnimationTrue.visible()
            binding.lottieAnimationTrue.setAnimation(R.raw.anim_true)
            audioPlay("correct_audio")

            binding.lottieAnimationTrue.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                }


                override fun onAnimationEnd(animation: Animator) {
                    binding.lottieAnimationTrue.gone()
                    binding.viwBlur.gone()
                }

                override fun onAnimationCancel(animation: Animator) {
                    binding.viwBlur.gone()
                    binding.lottieAnimationTrue.gone()
                }

                override fun onAnimationRepeat(animation: Animator) {
                }
            })
           audioPlay("correct_audio")
            binding.lottieAnimationTrue.playAnimation()

        } else {
            binding.viwBlur.visible()
            binding.lottieAnimationTrue.visible()
            binding.lottieAnimationTrue.setAnimation(R.raw.false_anim)
            binding.lottieAnimationTrue.playAnimation()


            Handler().postDelayed({
                binding.lottieAnimationTrue.gone()
                binding.viwBlur.gone()
            }, 750)
        }
    }

    fun audioPlay(count:String) {
        when(count){
            "correct_audio"->    amirAudio.playRaw(R.raw.correct_audio.toString())
            "finish_audio"->    amirAudio.playRaw(R.raw.finish_audio.toString())
        }
    }


    private fun vibrateDevice(durationMillis: Long) {
        // Get the vibrator service
        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        // Check if the device has a vibrator
        if (vibrator.hasVibrator()) {
            // Vibrate the device with the specified duration
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        durationMillis,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(durationMillis)
            }
        }
    }
}





