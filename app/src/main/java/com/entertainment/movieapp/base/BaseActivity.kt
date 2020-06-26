package com.entertainment.movieapp.base

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.ccpp.shared.network.repository.prefs.PreferenceStorage
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


open class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var preferenceStorage: PreferenceStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "MyNotification",
                "MyNotification",
                NotificationManager.IMPORTANCE_HIGH
            )

            val manager: NotificationManager = getSystemService(NotificationManager::class.java)!!
            manager.createNotificationChannel(channel)
        }
        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().subscribeToTopic("general")
            .addOnCompleteListener { task ->
                var msg = "Successful"
                if (!task.isSuccessful) {
                    msg = "failed"
                }
                Log.d("TAG", msg)
                //   Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
        getDeviceToken()

    }

    fun getDeviceToken() {
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this) { instanceIdResult ->
            val newToken = instanceIdResult.token
            Log.e("newToken", newToken)
            preferenceStorage.deviceToken = newToken
        }
    }


}