package com.example.erteklerqosqlarhmtaqmaqlar.view.question

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.erteklerqosqlarhmtaqmaqlar.MainActivity
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.local.Constants
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.OnlineTest
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.Test
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentQuestionBinding
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.network.NetworkViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.utils.MyTimer
import com.example.erteklerqosqlarhmtaqmaqlar.utils.TAG
import com.example.erteklerqosqlarhmtaqmaqlar.utils.audioplayer.AndroidAudioPlayer
import com.example.erteklerqosqlarhmtaqmaqlar.utils.audiorecorder.AndroidAudioRecorder
import com.example.erteklerqosqlarhmtaqmaqlar.utils.getUserToken
import com.example.erteklerqosqlarhmtaqmaqlar.utils.glide
import com.example.erteklerqosqlarhmtaqmaqlar.utils.gone
import com.example.erteklerqosqlarhmtaqmaqlar.utils.toRequestData
import com.example.erteklerqosqlarhmtaqmaqlar.utils.toTime
import com.example.erteklerqosqlarhmtaqmaqlar.utils.visible
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class QuestionFragment : Fragment(R.layout.fragment_question) {
    private lateinit var binding: FragmentQuestionBinding
    private lateinit var mainActivity: MainActivity
    private val networkViewModel: NetworkViewModel by viewModel()
    private val myTimer = MyTimer()
    private var isPlaying = false
    private val recorder by lazy {
        AndroidAudioRecorder(requireActivity().applicationContext)
    }
    private val player by lazy {
        AndroidAudioPlayer(requireActivity().applicationContext)
    }
    private var audioFile: File? = null
    private lateinit var a: File
    private var time = 0
    private var testId = 0
    private lateinit var testList: MutableList<OnlineTest>
    private var size = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuestionBinding.bind(view)
        mainActivity = requireActivity() as MainActivity

        initListeners()

        initObservers()

        foto_qosiw()

        binding.llPlay.setOnClickListener {
            isPlaying = !isPlaying
            if (isPlaying) {
                binding.ivPlay.gone()
                binding.ivPause.visible()
                player.playRaw(R.raw.oy_pikir_qaldirin.toString())
                startChecking()
            } else {
                binding.ivPause.gone()
                binding.ivPlay.visible()
                player.stop()
                stopChecking()
            }
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

    private fun stopChecking() {
        try {
            myTimer.stop()
        } catch (e: Exception) {
            Log.e("Amir", "Error in stopChecking: ${e.message}", e)
        }
    }



    fun foto_qosiw(){

        // Генерируем случайное число от 1 до 16
        val randomImageNumber = (1..16).random()

        // Используем when для выбора идентификатора ресурса
        val imageResId = when (randomImageNumber) {
            1 -> R.drawable.garga_menen_pers1
            2 -> R.drawable.qoraz_menen_tulki_qoraz
            3 -> R.drawable.aqilli_otinshi_pers4
            4 -> R.drawable.dau_menen_bala_bala
            5 -> R.drawable.jigit_ham_qarligash_pers3
            6 -> R.drawable.iyttin_nesiybesi_pers2
            7 -> R.drawable.arzu_armanina_jetken_jigit_pers2
            8 -> R.drawable.arzu_armanina_jetken_jigit_pers2
            9 -> R.drawable.ilimpaz_pers1
            10 -> R.drawable.aqilli_serke_ham_aqmaq_arislan_pers_3
            11 -> R.drawable.dunyanin_zori_pers_6
            12 -> R.drawable.jetim_bala_pers_1
            13 -> R.drawable.qis_penen_jaz_qalay_janjellesti_pers_2
            14 -> R.drawable.maqtanshaq_tishqan_pers_1
            15 -> R.drawable.jolbaris_ham_eshek_pers_2
            16 -> R.drawable.awizbirlik_pers_1
            else -> R.drawable.awizbirlik_pers_1 // Этот случай не должен возникнуть, но добавлен для полноты
        }

        if (imageResId != 0) {
            // Устанавливаем изображение в ImageView
            binding.ivImage.setImageResource(imageResId)
            ///   Toast.makeText(this@QuestionFragment.requireContext(), "Фото загружено", Toast.LENGTH_SHORT).show()
        } else {
            // Если ресурс не найден, показываем сообщение об ошибке
            Toast.makeText(this@QuestionFragment.requireContext(), "Ошибка загрузки изображения", Toast.LENGTH_SHORT).show()
        }

        // Показываем CardView в любом случае
        binding.cardView.visibility = View.VISIBLE
    }








    private fun initObservers() {
        networkViewModel.sendAudioLiveData.observe(mainActivity) {
            Toast.makeText(requireActivity(), "Tabıslı jiberildi!", Toast.LENGTH_SHORT).show()
            MainScope().launch {
                networkViewModel.sendNotification(
                    toRequestData(
                        "Ertekler",
                        "Sizge jańa xabar keldi"
                    ), Constants.SERVER_KEY
                )
            }
        }

        networkViewModel.notificationResponseLiveData.observe(mainActivity) {
            Toast.makeText(requireActivity(), it.success.toString(), Toast.LENGTH_SHORT).show()
            Log.d(TAG, "initObservers: ${it.success} ${it.failure} ${it.result}")
        }

        networkViewModel.allTestLiveData.observe(mainActivity) {
            testList = it.data.toMutableList()
            size = testList.size
            if (testId < size) {
                val test = testList[testId]
                if (test.imageUrl != "null") {
                    binding.ivImage.glide(testList[testId].imageUrl)
                } else binding.cardView.gone()
                player.playURI(testList[testId].audioUrl)
            }
        }

        MainScope().launch {
            networkViewModel.allTests()
        }
    }

    private fun initListeners() {
        binding.fabStartRecording.setOnClickListener {
            a = File(requireActivity().cacheDir, "audio.mp3").also {
                recorder.start(it)
                audioFile = it
            }

            binding.fabStartRecording.gone()
            binding.fabStopRecording.visible()
            myTimer.start {
                time += 100
                if (time >= 2 * 60 * 1000) {
                    recorder.stop()
                    binding.fabStopRecording.gone()
                    binding.fabSendRecording.visible()
                    binding.ivDelete.visible()
                    binding.ivListen.visible()
                    myTimer.stop()
                } else {
                    binding.textviewRecordingTime.text = time.toTime()
                }
            }
        }

        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.fabStopRecording.setOnClickListener {
            recorder.stop()
            binding.fabStopRecording.gone()
            binding.fabSendRecording.visible()
            binding.ivDelete.visible()
            binding.ivListen.visible()
            myTimer.stop()
        }

        binding.fabSendRecording.setOnClickListener {
            if (audioFile != null) {
                MainScope().launch {
                    delay(500)
                    try {
                        networkViewModel.sendAudio(getUserToken(requireActivity()), "1", a, "Voice")
                    } catch (e: Exception) {
                        Toast.makeText(
                            requireActivity(),
                            "Something went wrong! Please try again...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(requireActivity(), "Qátelik júz berdi!", Toast.LENGTH_SHORT).show()
            }
            binding.fabSendRecording.gone()
            binding.fabStartRecording.visible()
            binding.fabStopRecording.gone()
            binding.ivDelete.gone()
            binding.ivListen.gone()
            time = 0
            binding.textviewRecordingTime.text = "00:00"
            myTimer.stop()
        }

        binding.ivDelete.setOnClickListener {
            binding.fabSendRecording.gone()
            binding.fabStartRecording.visible()
            binding.fabStopRecording.gone()
            binding.ivDelete.gone()
            binding.ivListen.gone()
            time = 0
            myTimer.stop()
            try {
                recorder.stop()
                player.stop()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.ivListen.setOnClickListener {
            if (audioFile != null) {
                player.playFile(audioFile ?: return@setOnClickListener)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myTimer.stop()
        try {
            recorder.stop()
            player.stop()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}