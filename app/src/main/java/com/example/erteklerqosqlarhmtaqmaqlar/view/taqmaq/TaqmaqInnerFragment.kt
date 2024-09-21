package com.example.erteklerqosqlarhmtaqmaqlar.view.taqmaq

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.erteklerqosqlarhmtaqmaqlar.MainActivity
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Taqmaq
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentInnerTaqmaqBinding
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MainViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.utils.MyTimer
import com.example.erteklerqosqlarhmtaqmaqlar.utils.TAG
import com.example.erteklerqosqlarhmtaqmaqlar.utils.gone
import com.example.erteklerqosqlarhmtaqmaqlar.utils.toTime
import com.example.erteklerqosqlarhmtaqmaqlar.utils.visible
import com.example.erteklerqosqlarhmtaqmaqlar.view.taqmaq.viewpager.FirstTaqmaqFragment
import com.example.erteklerqosqlarhmtaqmaqlar.view.taqmaq.viewpager.SecondTaqmaqFragment
import com.example.erteklerqosqlarhmtaqmaqlar.view.taqmaq.viewpager.TaqmaqViewPagerAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException

class TaqmaqInnerFragment : Fragment(R.layout.fragment_inner_taqmaq) {

    private lateinit var binding: FragmentInnerTaqmaqBinding
    private var itemId = -1
    private lateinit var mainActivity: MainActivity
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var taqmaq: Taqmaq
    private lateinit var mediaPlayer: MediaPlayer
    private val myTimer = MyTimer()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentInnerTaqmaqBinding.bind(view)
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)
        itemId = arguments?.getInt("item_id") ?: -1

        // Создаем MediaPlayer вручную
        mediaPlayer = MediaPlayer.create(
            requireActivity(),
            R.raw.sanamalar_awelemen
        )

        if (itemId != -1) {
            initListeners()

            binding.seekBar.progress = 0
            binding.seekBar.max = mediaPlayer.duration
            binding.tvDurationTime.text = mediaPlayer.duration.toTime()
            binding.tvCurrentTime.text = "00:00"
            initObservers()
          //  Toast.makeText(this@TaqmaqInnerFragment.requireContext(),"Kirdi",Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(mainActivity, "Qátelik júz berdi!", Toast.LENGTH_SHORT).show()
            mainActivity.onBackPressed()
        }
    }


    private fun initObservers() {
        mainViewModel.byIdTaqmaqLiveData.observe(mainActivity) { taqmaq ->
            this.taqmaq = taqmaq
            viewPager(taqmaq)
            Log.d(TAG, "initObservers: ${taqmaq.audio}")

            if (::mediaPlayer.isInitialized) {
                mediaPlayer.release()
            }

            mediaPlayer = MediaPlayer().apply {


                try {
                    // Получаем идентификатор ресурса по имени
                    val resourceId = resources.getIdentifier(taqmaq.audio, "raw", requireContext().packageName)
                    if (resourceId != 0) {
                        setDataSource(requireContext(), Uri.parse("android.resource://${requireContext().packageName}/$resourceId"))
                        prepareAsync()
                        setOnPreparedListener {
                            binding.seekBar.max = duration
                            binding.tvDurationTime.text = duration.toTime()
                            binding.tvCurrentTime.text = "00:00"
                            // MediaPlayer готов к использованию
                            Log.d(TAG, "MediaPlayer prepared successfully")
                        }
                        setOnErrorListener { mp, what, extra ->
                            val errorMessage = when(what) {
                                MediaPlayer.MEDIA_ERROR_UNKNOWN -> "Неизвестная ошибка"
                                MediaPlayer.MEDIA_ERROR_SERVER_DIED -> "Сервер недоступен"
                                else -> "Ошибка код: $what, extra: $extra"
                            }
                            Log.e(TAG, "MediaPlayer error: $errorMessage")
                            Toast.makeText(context, "Ошибка воспроизведения: $errorMessage", Toast.LENGTH_SHORT).show()
                            true
                        }
                    } else {
                        throw IllegalArgumentException("Аудиофайл не найден: ${taqmaq.audio}")
                    }
                } catch (e: IOException) {
                    Log.e(TAG, "Error setting data source: ${e.message}", e)
                    Toast.makeText(context, "Не удалось загрузить аудио: ${e.message}", Toast.LENGTH_SHORT).show()
                } catch (e: IllegalArgumentException) {
                    Log.e(TAG, "Invalid audio source: ${e.message}", e)
                    Toast.makeText(context, "Неверный источник аудио: ${e.message}", Toast.LENGTH_SHORT).show()
                } catch (e: SecurityException) {
                    Log.e(TAG, "Security error: ${e.message}", e)
                    Toast.makeText(context, "Ошибка доступа к аудио: ${e.message}", Toast.LENGTH_SHORT).show()
                } catch (e: IllegalStateException) {
                    Log.e(TAG, "IllegalStateException: ${e.message}", e)
                    Toast.makeText(context, "Ошибка состояния MediaPlayer: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        MainScope().launch {
            mainViewModel.getByIdTaqmaq(itemId)
        }
    }

    private fun viewPager(q: Taqmaq) {
        val fragments = listOf(FirstTaqmaqFragment(q), SecondTaqmaqFragment(q))
        val adapter = TaqmaqViewPagerAdapter(fragments, requireFragmentManager(), lifecycle)
        binding.viewPager.adapter = adapter
    }

    private fun initListeners() {

//        binding.btnGoTest.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_qosiqInnerFragment_to_testFragment
//            )
//        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.ivPlay.setOnClickListener {
            mediaPlayer.start()
            binding.ivPlay.gone()
            binding.ivResume.visible()
        }

        binding.ivResume.setOnClickListener {
            mediaPlayer.pause()
            binding.ivPlay.visible()
            binding.ivResume.gone()
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        myTimer.start {
            if (mediaPlayer.isPlaying) {
                binding.seekBar.progress = mediaPlayer.currentPosition
                binding.tvCurrentTime.text = mediaPlayer.currentPosition.toTime()
            } else {
                myTimer.stop()
            }
        }

        mediaPlayer.setOnCompletionListener {
            binding.ivPlay.visible()
            binding.ivResume.gone()
            binding.seekBar.progress = 0
        }

        binding.ivSkip5.setOnClickListener {
            mediaPlayer.seekTo(mediaPlayer.currentPosition + 5000)
        }
        binding.ivForward5.setOnClickListener {
            mediaPlayer.seekTo(mediaPlayer.currentPosition - 5000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.settingsBottomNavigation(true)
        mediaPlayer.stop()
        mediaPlayer.release()
        myTimer.stop()
    }
}