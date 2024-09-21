package com.example.erteklerqosqlarhmtaqmaqlar

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.erteklerqosqlarhmtaqmaqlar.data.local.Constants
import com.example.erteklerqosqlarhmtaqmaqlar.databinding.ActivityMainBinding
import com.example.erteklerqosqlarhmtaqmaqlar.utils.TAG
import com.example.erteklerqosqlarhmtaqmaqlar.utils.gone
import com.example.erteklerqosqlarhmtaqmaqlar.utils.sendTokenToFirebase
import com.example.erteklerqosqlarhmtaqmaqlar.utils.visible
import com.example.erteklerqosqlarhmtaqmaqlar.view.MusicService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val RECORD_AUDIO_REQUEST_CODE = 101
    var bookID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController =
            (supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment).navController

        requestPermission()
        setBottomNavigation()

        try {
            getToken()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Запуск сервиса
        val intent = Intent(this, MusicService::class.java)
        startService(intent)



    navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.ertekFragment,
                R.id.qosiqFragment,
                R.id.taqmaqFragment,
                R.id.messageNewsFragment,
                R.id.favoritesTaqmaqFragment,
                R.id.favoritesErtekFragment,
                R.id.favoritesQosiqFragment  -> {
                    // Запуск музыки, когда находимся на одном из главных экранов
                    val intent = Intent(this, MusicService::class.java)
                    startService(intent)
                }
                else -> {
                    // Остановка музыки, когда находимся на внутренних экранах
                    val intent = Intent(this, MusicService::class.java)
                    stopService(intent)
                }
            }
        }
    }





    override fun onResume() {
        super.onResume()
        // Запуск сервиса при возврате к активности
        val intent = Intent(this, MusicService::class.java)
        startService(intent)
    }

    override fun onPause() {
        super.onPause()
        // Остановка сервиса при покидании активности
        val intent = Intent(this, MusicService::class.java)
        stopService(intent)
    }

    private fun setBottomNavigation() {
        binding.navView.setMenuItems(Constants.items, 0)
        binding.navView.onMenuItemClick(0)
        if (::navController.isInitialized) {
            binding.navView.setupWithNavController(navController)
        }
    }

    fun settingsBottomNavigation(show: Boolean) {
        if (show) {
            binding.navView.visible()
        } else binding.navView.gone()
    }

    private fun getToken() {
        sendTokenToFirebase(this)
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RECORD_AUDIO_REQUEST_CODE) {
            if (grantResults.size == 3 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: Ruxsat bar")
            } else {
                Toast.makeText(
                    this,
                    "Siz telefonıńızda audio fayllarǵa kiriwge ruxsat beriwińiz kerek!",
                    Toast.LENGTH_SHORT
                ).show()
                finishAffinity()
            }
        }
    }

    fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )
    }
}