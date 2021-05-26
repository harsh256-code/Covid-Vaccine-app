package com.example.covidvaccine

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var searchcenter: Button
    lateinit var casesButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchcenter = findViewById(R.id.Search_center)
        casesButton = findViewById(R.id.Cases)
        searchcenter.setOnClickListener {
            val intent: Intent = Intent(this@MainActivity, VaccineTracker::class.java)
            startActivity(intent)
        }
        casesButton.setOnClickListener {
            val intent = Intent(this@MainActivity, CovidCases::class.java)
            startActivity(intent)
        }


    }
}
