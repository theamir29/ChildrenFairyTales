package com.example.erteklerqosqlarhmtaqmaqlar.utils

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.erteklerqosqlarhmtaqmaqlar.R
import com.example.erteklerqosqlarhmtaqmaqlar.data.local.Constants
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.User
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.Data
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.Notification
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.RequestData
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.messaging.FirebaseMessaging
import kotlin.random.Random

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.setImage(name: String) {
    this.setImageURI(Uri.parse("android.resource://com.example.erteklerqosqlarhmtaqmaqlar/drawable/$name"))
}

const val TAG = "TTTT"

fun toRequestData(title: String, body: String) = RequestData(
    Constants.ADMIN_TOKEN,
    Notification(
        title,
        body,
        true,
        "Tri-tone"
    ),
    Data(
        "",
        ""
    )
)

fun saveUserToken(context: Context, token: String) {
    val pref = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE)
    pref.edit().putString("user_token", token).apply()
}

fun getUserToken(context: Context): String {
    val pref = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE)
    return pref.getString("user_token", "null") ?: "null"
}

fun sendTokenToFirebase(context: Context) {
    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
        if (task.isSuccessful.not()) {
            return@addOnCompleteListener
        } else {
            val token = task.result

            saveUserToken(context, token)
            Firebase.database.getReference("users").child(token)
                .setValue(
                    User(
                        "User${Random.nextInt(100, 999)}",
                        System.currentTimeMillis().toString(),
                        "User"
                    )
                )
                .addOnSuccessListener {
                    Log.d(TAG, "getToken: Success")
                }
                .addOnFailureListener {
                    Log.d(TAG, "getToken: Failure")
                }
//                Log.d(TAG, "getToken: $token")
//                Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
        }
    }
}

fun vibrate(milliSecond: Int, context: Context) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(
            VibrationEffect.createOneShot(
                milliSecond.toLong(),
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        )
    } else vibrator.vibrate(milliSecond.toLong())
}

fun Int.toTime(): String {
    val time = this / 1000
    val minute = time / 60
    val second = time % 60
    return "00${minute}".takeLast(2) + ":" + "00${second}".takeLast(2)
}

fun String.format(): Int {
    return when (this) {
        "yaramazan " -> R.raw.yaramazan
        "tawlamashlar_tulkishek" -> R.raw.tawlamashlar_tulkishek
        "sanamashlar" -> R.raw.sanamashlar
        "sanamaler_awelemen" -> R.raw.sanamashlar
        "janalikler_haq_da_qosiqlar" -> R.raw.janlikler_haq_da_qosiqlar
        else -> R.raw.yaramazan
    }
}

fun Fragment.snackBar(message: String?) {
    Snackbar.make(requireView(), message ?: "", Snackbar.LENGTH_SHORT).show()
}

fun Fragment.toastMessage(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun ImageView.glide(url: String){
    Glide.with(this.context)
        .load(url)
        .into(this)
}