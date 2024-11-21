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

class WeightLifting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weight_lifting)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initializing buttons
        val etCaloriesBurned = findViewById<EditText>(R.id.etCaloriesBurned)
        val etSets = findViewById<EditText>(R.id.etPace) // Using etPace for "Sets/Reps"
        val etWeight = findViewById<EditText>(R.id.etDistance) // Using etDistance for "Weight (kg/lbs)"
        val etTime = findViewById<EditText>(R.id.etTime)
        val etSpeed = findViewById<EditText>(R.id.etSpeed)
        val btnSubmitRunning = findViewById<Button>(R.id.btnSubmitRunning)

        // Handleing Save button
        btnSubmitRunning.setOnClickListener {
            val calories = etCaloriesBurned.text.toString().trim()
            val sets = etSets.text.toString().trim()
            val weight = etWeight.text.toString().trim()
            val time = etTime.text.toString().trim()
            val speed = etSpeed.text.toString().trim()

            // Checking input types
            if (calories.isEmpty() || sets.isEmpty() || weight.isEmpty() || time.isEmpty() || speed.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_SHORT).show()
            } else {
                saveActivityData(calories, sets, weight, time, speed)
                Toast.makeText(this, "Activity Saved!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    // Saving activity data to activities.txt
    private fun saveActivityData(calories: String, sets: String, weight: String, time: String, speed: String) {
        val fileName = "activities.txt"
        val fileContent = """
            Activity: WeightLifting
            Calories Burned: $calories
            Sets/Reps: $sets
            Weight: $weight kg/lbs
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
