package com.example.erteklerqosqlarhmtaqmaqlar.view.testQosiqlar

import android.animation.Animator
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieCompositionFactory
import com.example.erteklerqosqlarhmtaqmaqlar.MainActivity
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.QosiqTest
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentQosiqTestBinding
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MainViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.utils.MyTimer
import com.example.erteklerqosqlarhmtaqmaqlar.utils.TAG
import com.example.erteklerqosqlarhmtaqmaqlar.utils.audioplayer.AndroidAudioPlayer
import com.example.erteklerqosqlarhmtaqmaqlar.utils.gone
import com.example.erteklerqosqlarhmtaqmaqlar.utils.visible
import com.example.erteklerqosqlarhmtaqmaqlar.view.qosiq.QosiqInnerFragment
import com.example.erteklerqosqlarhmtaqmaqlar.view.test.TestFragmentDirections
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class TestQosiqFragment2 : Fragment(R.layout.fragment_qosiq_test) {
    private var isAnswerChecked = false
    private lateinit var amirAudio: AndroidAudioPlayer
    private var correctAnswersCount = 0
    private lateinit var binding: FragmentQosiqTestBinding
    private lateinit var mainActivity: MainActivity
    private val mainViewModel: MainViewModel by viewModel()
    private var currentQuestionId = 0
    private var selectedAnswerId = -1
    private lateinit var currentQuestion: QosiqTest
    private var score = 0
    private val myTimer = MyTimer()
    private var isPlaying = false
    private var correctAnswer = ""
    private var selectedAnswer = ""
    private val answersList = mutableListOf<String>()
    private val player by lazy {
        AndroidAudioPlayer(requireActivity())
    }
    private val selectedQuestions = mutableListOf<QosiqTest>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            binding = FragmentQosiqTestBinding.bind(view)
            (requireActivity() as? MainActivity)?.let { mainActivity ->
                mainActivity.settingsBottomNavigation(false)

                // Add this block to configure Lottie
                LottieCompositionFactory.setMaxCacheSize(20)
                binding.lottieAnimationTrue.setFailureListener { exception ->
                    Log.e("Amir", "Lottie animation failed to load", exception)
                }

                amirAudio = AndroidAudioPlayer(this@TestQosiqFragment2.requireContext())

                initListeners()
                setupListeners()
                initObservers()
            } ?: run {
                Log.e("Amir", "Error: Activity is not MainActivity")
            }
        } catch (e: Exception) {
            Log.e("Amir", "Error in onViewCreated: ${e.message}", e)
        }
    }

    private fun setupListeners() {
        binding.apply {
            cvAnswer1.setOnClickListener {
                optionSelected(strokeAnswer1)
                selectedAnswerId = 0
                selectedAnswer = answersList[0]
            }

            cvAnswer2.setOnClickListener {
                optionSelected(strokeAnswer2)
                selectedAnswerId = 1
                selectedAnswer = answersList[1]
            }

            cvAnswer3.setOnClickListener {
                optionSelected(strokeAnswer3)
                selectedAnswerId = 2
                selectedAnswer = answersList[2]
            }

            cvAnswer4.setOnClickListener {
                optionSelected(strokeAnswer4)
                selectedAnswerId = 3
                selectedAnswer = answersList[3]
            }
        }
    }

    private fun optionSelected(strokeAnswerView: View) {
        binding.apply {
            strokeAnswer1.setBackgroundResource(R.color.default_color_answer)
            strokeAnswer2.setBackgroundResource(R.color.default_color_answer)
            strokeAnswer3.setBackgroundResource(R.color.default_color_answer)
            strokeAnswer4.setBackgroundResource(R.color.default_color_answer)
        }

        strokeAnswerView.setBackgroundResource(R.color.selected_color_answer)
    }

    private fun checkAnswer() {
        if (selectedAnswerId == -1 || isAnswerChecked) {
            return
        }

        isAnswerChecked = true

        binding.apply {
            if (correctAnswer != selectedAnswer) {
                answer(false)
                answerView(selectedAnswerId, R.color.uncorrect_answer)
            } else {
                answer(true)
                score++
                binding.tvTestNumber.text = score.toString()
            }
            when (correctAnswer) {
                answersList[0] -> answerView(0, R.color.correct_answer)
                answersList[1] -> answerView(1, R.color.correct_answer)
                answersList[2] -> answerView(2, R.color.correct_answer)
                answersList[3] -> answerView(3, R.color.correct_answer)
            }

            cvAnswer1.isEnabled = false
            cvAnswer2.isEnabled = false
            cvAnswer3.isEnabled = false
            cvAnswer4.isEnabled = false
            selectedAnswerId = -1
            selectedAnswer = ""
            correctAnswer = ""
        }
    }

    private fun answerView(answer: Int, drawable: Int) {
        binding.apply {
            when (answer) {
                0 -> strokeAnswer1.setBackgroundResource(drawable)
                1 -> strokeAnswer2.setBackgroundResource(drawable)
                2 -> strokeAnswer3.setBackgroundResource(drawable)
                3 -> strokeAnswer4.setBackgroundResource(drawable)
            }
        }
    }

    private fun initListeners() {
        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.llPlay.setOnClickListener {
            isPlaying = !isPlaying
            if (isPlaying) {
                binding.ivPlay.gone()
                binding.ivPause.visible()
                player.playRaw(currentQuestion.audio)
                startChecking()
            } else {
                binding.ivPause.gone()
                binding.ivPlay.visible()
                player.stop()
                stopChecking()
            }
        }

        binding.btnNext.setOnClickListener {
            try {
                when (binding.btnNext.text) {
                    getString(R.string.text_submit) -> {
                        if (!isAnswerChecked) {
                            checkAnswer()
                        }
                    }

                    getString(R.string.finish_text) ->
                     //   TestFragmentDirections.actionTestFragmentToMatchingFragment()
                        Toast.makeText(context, "finish", Toast.LENGTH_SHORT).show()


                    else -> {
                        if (currentQuestionId >= selectedQuestions.size) {
                            if (findNavController().currentDestination?.id == R.id.testFragment) {
                                Toast.makeText(context, "finish", Toast.LENGTH_SHORT).show()
                            } else {
                                Log.e("Amir", "Current destination is not TestFragment")
                            }
                        } else {
                            moveToNextQuestionOrFinish()
                        }
                    }
                }
                player.stop()
            } catch (e: Exception) {
                Log.e("Amir", "Error in btnNext onClick: ${e.message}", e)
            }
        }
    }

    private fun initObservers() {
        mainViewModel.qosiqTestsLiveData.observe(viewLifecycleOwner) { questions ->
            selectedQuestions.clear()
            selectedQuestions.addAll(
                questions.shuffled().take(5)
            ) // Убедимся, что выбираем 5 вопросов
            currentQuestionId = 0 // Обнуляем индекс текущего вопроса
            loadQuestion()
        }

        mainViewModel.qosiqTestByIdLiveData.observe(viewLifecycleOwner) { question ->
            currentQuestion = question
            displayQuestion()
        }

        MainScope().launch {
            try {
                mainViewModel.getAllQosiqTests()
            } catch (e: Exception) {
                Log.e("Amir", "Error in initObservers: ${e.message}", e)
            }
        }
    }


    private fun loadQuestion() {
        try {
            currentQuestion = selectedQuestions[currentQuestionId]
            displayQuestion()
            isAnswerChecked = false
        } catch (e: Exception) {
            Log.e("Amir", "Error in loadQuestion: ${e.message}", e)
            showErrorMessage("Failed to load question. Please try again.")
        }
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun displayQuestion() {
        try {
            binding.tvTestNumber.text = currentQuestion.id.toString()
            val image = currentQuestion.image ?: ""
            if (image.isNotEmpty()) {
                binding.cvQuestion.visible()
                val drawable = stringToDrawable(requireContext(), image)
                binding.ivQuestion.setImageDrawable(drawable)
            } else {
                binding.cvQuestion.gone()
            }

            val allAnswers = currentQuestion.answers.split("|")
            val shuffledAnswers = allAnswers.shuffled()
            val correctAnswerIndex = shuffledAnswers.indexOf(allAnswers[0])
            correctAnswer = shuffledAnswers[correctAnswerIndex]

            binding.apply {
                ivAnswer1.setImageDrawable(stringToDrawable(requireContext(), shuffledAnswers[0]))
                ivAnswer2.setImageDrawable(stringToDrawable(requireContext(), shuffledAnswers[1]))
                ivAnswer3.setImageDrawable(stringToDrawable(requireContext(), shuffledAnswers[2]))
                ivAnswer4.setImageDrawable(stringToDrawable(requireContext(), shuffledAnswers[3]))

                answersList.clear()
                answersList.addAll(shuffledAnswers)

                cvAnswer1.isEnabled = true
                cvAnswer2.isEnabled = true
                cvAnswer3.isEnabled = true
                cvAnswer4.isEnabled = true

                strokeAnswer1.setBackgroundResource(R.color.default_color_answer)
                strokeAnswer2.setBackgroundResource(R.color.default_color_answer)
                strokeAnswer3.setBackgroundResource(R.color.default_color_answer)
                strokeAnswer4.setBackgroundResource(R.color.default_color_answer)

                btnNext.text = getString(R.string.text_submit)
            }
        } catch (e: Exception) {
            Log.e("Amir", "Error in displayQuestion: ${e.message}", e)
        }

    }


    private fun stringToDrawable(context: Context, imageName: String): Drawable? {
        val resId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        return if (resId != 0) {
            ContextCompat.getDrawable(context, resId)
        } else {
            null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
        myTimer.stop()
    }


    private fun answer(result: Boolean) {

        try {
            if (result) {
                correctAnswersCount++
                audioCheck("correct_audio")
                binding.viwBlur.visible()
                binding.lottieAnimationTrue.visible()
                binding.lottieAnimationTrue.setAnimation(R.raw.anim_true)
                binding.lottieAnimationTrue.setSpeed(2f)

                binding.lottieAnimationTrue.addAnimatorListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {


                        binding.btnNext.isEnabled = false
                        binding.llPlay.isEnabled = false
                        binding.cvAnswer1.isEnabled = false
                        binding.cvAnswer2.isEnabled = false
                        binding.cvAnswer3.isEnabled = false
                        binding.cvAnswer4.isEnabled = false

                    }


                    override fun onAnimationEnd(animation: Animator) {
                        binding.btnNext.isEnabled = true
                        binding.llPlay.isEnabled = true
                        binding.lottieAnimationTrue.gone()
                        binding.viwBlur.gone()
                        moveToNextQuestionOrFinish()

                    }

                    override fun onAnimationCancel(animation: Animator) {
                        binding.btnNext.isEnabled = true
                        binding.llPlay.isEnabled = true
                        binding.viwBlur.gone()
                        binding.lottieAnimationTrue.gone()
                        moveToNextQuestionOrFinish()
                    }

                    override fun onAnimationRepeat(animation: Animator) {}
                })

                binding.lottieAnimationTrue.playAnimation()

            } else {
                binding.viwBlur.visible()
                binding.lottieAnimationTrue.visible()
                binding.lottieAnimationTrue.setAnimation(R.raw.anim_false)
                binding.lottieAnimationTrue.setSpeed(2f)
                binding.lottieAnimationTrue.playAnimation()
                vibrateDevice(300)

                binding.lottieAnimationTrue.addAnimatorListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {
                        binding.btnNext.isEnabled = false
                        binding.llPlay.isEnabled = false
                        binding.cvAnswer1.isEnabled = false
                        binding.cvAnswer2.isEnabled = false
                        binding.cvAnswer3.isEnabled = false
                        binding.cvAnswer4.isEnabled = false
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        binding.btnNext.isEnabled = true
                        binding.llPlay.isEnabled = true
                        binding.viwBlur.gone()
                        binding.lottieAnimationTrue.gone()
                        moveToNextQuestionOrFinish()
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        binding.btnNext.isEnabled = true
                        binding.llPlay.isEnabled = true
                        binding.viwBlur.gone()
                        binding.lottieAnimationTrue.gone()
                        moveToNextQuestionOrFinish()
                    }

                    override fun onAnimationRepeat(animation: Animator) {}
                })
            }

            // После анимации правильности ответа
            binding.lottieAnimationTrue.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    if (currentQuestionId >= 5) { // Если это был последний вопрос
                        showFinalAnimation()
                    } else {
                        moveToNextQuestionOrFinish()
                    }
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })


        } catch (e: Exception) {
            Log.e("Amir", "Error in answer: ${e.message}", e)
        }
    }


    private fun showFinalAnimation() {

        Log.d(TAG, "Starting final animation")
        binding.viwBlur.visible()
        binding.lottieAnimationCandies.visible()

        val totalScoreAnimation = when (correctAnswersCount) {
            0 -> R.raw.one_star
            1 -> R.raw.one_star
            2 -> R.raw.two_star
            3 -> R.raw.three_star
            4 -> R.raw.four_star
            5 -> R.raw.five_star
            else -> R.raw.one_star
        }
        audioCheck("finish_audio")
        binding.lottieAnimationCandies.setAnimation(totalScoreAnimation)
        binding.lottieAnimationCandies.setSpeed(0.6f)
        binding.lottieAnimationCandies.scaleX = 2f
        binding.lottieAnimationCandies.scaleY = 2f
        binding.lottieAnimationStars.setAnimation(R.raw.anim_star_back2)
        binding.lottieAnimationStars.scaleX = 2f
        binding.lottieAnimationStars.scaleY = 2f
        binding.lottieAnimationCandies.playAnimation()
        binding.lottieAnimationStars.playAnimation()



        binding.lottieAnimationCandies.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                Log.d("Amir", "Final score animation started")
                binding.btnNext.isEnabled = false
                binding.llPlay.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator) {
                Log.d("Amir", "Final score animation ended, navigating to next fragment")
                binding.btnNext.isEnabled = true
                binding.llPlay.isEnabled = true
                binding.viwBlur.gone()
                binding.lottieAnimationTrue.gone()
                findNavController().popBackStack(R.id.qosiqInnerFragment, false)
            }

            override fun onAnimationCancel(animation: Animator) {
                Log.d("Amir", "Final score animation cancelled, navigating to next fragment")
                findNavController().popBackStack(R.id.qosiqInnerFragment, false)

            }

            override fun onAnimationRepeat(animation: Animator) {}
        })
    }


    private var hasNavigated = false
    private fun navigateToNextFragment() {
        if (!hasNavigated) {
            hasNavigated = true
            view?.post {
                if (findNavController().currentDestination?.id == R.id.testFragment) {
                    findNavController().navigate(TestFragmentDirections.actionTestFragmentToMatchingFragment())
                } else {
                    Log.e("Амир", "Текущий пункт назначения не TestFragment")
                }
            }
        }
    }


    private fun moveToNextQuestionOrFinish() {
        try {
            if (!isAnswerChecked) {
                Log.d("Amir", "Answer not checked yet")
                return
            }

            currentQuestionId++
            Log.d("Amir", "Moving to question $currentQuestionId")

            if (currentQuestionId >= 5) {
                Log.d("Amir", "All questions answered, showing final animation")
                showFinalAnimation()
            } else {
                Log.d("Amir", "Loading next question")
                loadQuestion()
            }

            isAnswerChecked = false
        } catch (e: Exception) {
            Log.e("Amir", "Error in moveToNextQuestionOrFinish: ${e.message}", e)
        }
    }


    private fun startChecking() {
        try {
            myTimer.start {
                if (player.isPlaying()) {
                    isPlaying = true
                    binding.ivPlay.gone()
                    binding.ivPause.visible()
                } else {
                    isPlaying = false
                    binding.ivPlay.visible()
                    binding.ivPause.gone()
                    myTimer.stop()

                }
            }
        } catch (e: Exception) {
            Log.e("Amir", "Error in startChecking: ${e.message}", e)
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

    fun audioCheck(count: String) {
        when (count) {
            "correct_audio" -> amirAudio.playRaw(R.raw.correct_audio.toString())
            "finish_audio" -> amirAudio.playRaw(R.raw.finish_audio.toString())
        }
    }


    private fun stopChecking() {
        try {
            myTimer.stop()
        } catch (e: Exception) {
            Log.e("Amir", "Error in stopChecking: ${e.message}", e)
        }
    }


}