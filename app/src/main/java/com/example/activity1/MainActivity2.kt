package com.example.activity1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import com.example.activity1.Running


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Button for Running
        findViewById<Button>(R.id.btnRunning).setOnClickListener {
            val intent = Intent(this, Running::class.java)
            startActivity(intent)
        }

        // Button for Cycling
        findViewById<Button>(R.id.btnCycling).setOnClickListener {
            val intent = Intent(this, Cycling::class.java)
            startActivity(intent)
        }

        // Button for Yoga
        findViewById<Button>(R.id.btnYoga).setOnClickListener {
            val intent = Intent(this, Yoga::class.java)
            startActivity(intent)
        }

        // Button for HIIT
        findViewById<Button>(R.id.btnHIIT).setOnClickListener {
            val intent = Intent(this, HIIT::class.java)
            startActivity(intent)
        }

        // Button for Weightlifting
        findViewById<Button>(R.id.btnWeightlifting).setOnClickListener {
            val intent = Intent(this, WeightLifting::class.java)
            startActivity(intent)
        }
    }
}
