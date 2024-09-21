package com.example.erteklerqosqlarhmtaqmaqlar.utils.audiorecorder

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}