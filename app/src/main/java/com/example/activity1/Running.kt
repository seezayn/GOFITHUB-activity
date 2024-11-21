package com.example.activity1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.activity1.R
import java.io.File

class Running : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_running)

        val etCaloriesBurned = findViewById<EditText>(R.id.etCaloriesBurned)
        val etPace = findViewById<EditText>(R.id.etPace)
        val etDistance = findViewById<EditText>(R.id.etDistance)
        val etTime = findViewById<EditText>(R.id.etTime)
        val etSpeed = findViewById<EditText>(R.id.etSpeed)
        val btnSubmitRunning = findViewById<Button>(R.id.btnSubmitRunning)

        // Handling Save Activity button
        btnSubmitRunning.setOnClickListener {
            val calories = etCaloriesBurned.text.toString().trim()
            val pace = etPace.text.toString().trim()
            val distance = etDistance.text.toString().trim()
            val time = etTime.text.toString().trim()
            val speed = etSpeed.text.toString().trim()

            // Checking input type
            if (calories.isEmpty() || pace.isEmpty() || distance.isEmpty() || time.isEmpty() || speed.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_SHORT).show()
            } else {
                // Save
                saveActivityData(calories, pace, distance, time, speed)
                Toast.makeText(this, "Activity Saved!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    // Saving activity data to activities.txt
    private fun saveActivityData(calories: String, pace: String, distance: String, time: String, speed: String) {
        val fileName = "activities.txt"
        val fileContent = """
            Activity: Running
            Calories Burned: $calories
            Pace: $pace min/mile
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
