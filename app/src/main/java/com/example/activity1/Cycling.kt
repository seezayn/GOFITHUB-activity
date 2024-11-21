package com.example.activity1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class Cycling : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cycling)

        // Adjust edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initializing  button
        val etCaloriesBurned = findViewById<EditText>(R.id.etCaloriesBurned)
        val etHeartRate = findViewById<EditText>(R.id.etHeartRate)
        val etDistance = findViewById<EditText>(R.id.etDistance)
        val etTime = findViewById<EditText>(R.id.etTime)
        val etSpeed = findViewById<EditText>(R.id.etSpeed)
        val btnSubmitRunning = findViewById<Button>(R.id.btnSubmitRunning)

        // Handling Save
        btnSubmitRunning.setOnClickListener {
            val calories = etCaloriesBurned.text.toString().trim()
            val heartRate = etHeartRate.text.toString().trim()
            val distance = etDistance.text.toString().trim()
            val time = etTime.text.toString().trim()
            val speed = etSpeed.text.toString().trim()

            if (calories.isEmpty() || heartRate.isEmpty() || distance.isEmpty() || time.isEmpty() || speed.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_SHORT).show()
            } else {
                // Saving input data
                saveActivityData(calories, heartRate, distance, time, speed)
                Toast.makeText(this, "Activity Saved!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    // Save activity data to activities.txt
    private fun saveActivityData(calories: String, heartRate: String, distance: String, time: String, speed: String) {
        val fileName = "activities.txt"
        val fileContent = """
            Activity: Cycling
            Calories Burned: $calories
            Heart Rate: $heartRate BPM
            Distance: $distance miles
            Time: $time
            Speed: $speed mph
            ------
        """.trimIndent()

        try {
            val file = File(filesDir, fileName)
            file.appendText(fileContent + "\n")
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to save activity: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
