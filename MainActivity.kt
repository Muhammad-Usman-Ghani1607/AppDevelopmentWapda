package com.example.wapda

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Handler(Looper.getMainLooper()).postDelayed({
            //val secondPage = Intent(this, MainActivity2::class.java)
            val secondPage = Intent(this, users::class.java)
            //val secondPage = Intent(this, MentorFeedBack::class.java)
            startActivity(secondPage)
            finish()

        }, 5000)
    }
}