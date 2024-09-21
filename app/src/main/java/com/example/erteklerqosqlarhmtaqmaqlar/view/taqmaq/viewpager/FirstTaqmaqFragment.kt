package com.example.erteklerqosqlarhmtaqmaqlar.view.taqmaq.viewpager

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Taqmaq
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentQosiqFirstBinding
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentTaqmaqBinding
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.FragmentTaqmaqlarFirstBinding
import com.example.erteklerqosqlarhmtaqmaqlar.utils.setImage

class FirstTaqmaqFragment(private val taqmaq: Taqmaq) : Fragment(R.layout.fragment_taqmaqlar_first) {
    private lateinit var binding: FragmentTaqmaqlarFirstBinding
    private val updateHandler: Handler = Handler()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTaqmaqlarFirstBinding.bind(view)
        binding.tvName.text = taqmaq.name
        loadImage()
    }

    private fun loadImage() {
        try {
            val resourceId = resources.getIdentifier(taqmaq.image, "drawable", requireContext().packageName)
            if (resourceId != 0) {
                binding.ivTitle.setImageResource(resourceId)
            } else {
                // Если изображение не найдено, можно установить placeholder или показать сообщение об ошибке
                binding.ivTitle.setImageResource(R.drawable.ertek_example)
                Toast.makeText(context, "Изображение не найдено: ${taqmaq.image}", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            // Обработка любых других ошибок при загрузке изображения
            Toast.makeText(context, "Ошибка при загрузке изображения: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


}