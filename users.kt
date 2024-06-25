package com.example.wapda

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button

class users : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_users)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find buttons by their IDs
        val sectionHeadButton = findViewById<Button>(R.id.section_head)
        val meterReaderButton = findViewById<Button>(R.id.Meter_Reader)

        // Set onClickListeners for the buttons
        sectionHeadButton.setOnClickListener {
            val intent = Intent(this, SectionHeadLogin::class.java)
            startActivity(intent)
        }

        meterReaderButton.setOnClickListener {
            val intent = Intent(this, MeterReaderLogin::class.java)
            startActivity(intent)
        }
    }
}
