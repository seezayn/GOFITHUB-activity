package com.example.activity1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initializing buttons
        val btnMainActivity2 = findViewById<MaterialButton>(R.id.btnMainActivity2)
        val btnRecentActivity = findViewById<MaterialButton>(R.id.btnRecentActivity)
        val btnGoals = findViewById<MaterialButton>(R.id.btnGoals)
        val btnMyProgress = findViewById<MaterialButton>(R.id.btnMyProgress)

        // Setting buttons
        btnMainActivity2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        btnRecentActivity.setOnClickListener {
            val intent = Intent(this, RecentActivity::class.java)
            startActivity(intent)
        }

        btnGoals.setOnClickListener {
            val intent = Intent(this, Goals::class.java)
            startActivity(intent)
        }

        btnMyProgress.setOnClickListener {
            val intent = Intent(this, MyProgress::class.java)
            startActivity(intent)
        }
    }
}
