package com.example.activity1

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import java.io.File

class MyProgress : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_progress)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //UI components
        val tvCaloriesBurned = findViewById<TextView>(R.id.tvCaloriesBurned)
        val tvTotalTimeSpent = findViewById<TextView>(R.id.tvTotalTimeSpent)
        val tvDistanceCovered = findViewById<TextView>(R.id.tvDistanceCovered)
        val btnRecentActivity = findViewById<MaterialButton>(R.id.btnRecentActivity)

        val summary = calculateProgressSummary()
        tvCaloriesBurned.text = "Calories Burned: ${summary.totalCalories} kcal"
        tvTotalTimeSpent.text = "Total Time Spent: ${summary.totalTime} hrs"
        tvDistanceCovered.text = "Distance Covered: ${summary.totalDistance} miles"

        //Recent Activity button
        btnRecentActivity.setOnClickListener {
            val intent = Intent(this, RecentActivity::class.java)
            startActivity(intent)
        }
    }

    // Calculate progress summary by reading activities.txt
    private fun calculateProgressSummary(): ProgressSummary {
        val fileName = "activities.txt"
        val file = File(filesDir, fileName)
        var totalCalories = 0
        var totalTime = 0.0
        var totalDistance = 0.0

        if (file.exists()) {
            file.forEachLine { line ->

                when {
                    line.startsWith("Calories Burned:") -> {
                        totalCalories += line.substringAfter("Calories Burned:").trim().removeSuffix("kcal").toIntOrNull() ?: 0
                    }
                    line.startsWith("Time:") -> {
                        val time = line.substringAfter("Time:").trim()
                        totalTime += parseTimeToHours(time)
                    }
                    line.startsWith("Distance:") -> {
                        totalDistance += line.substringAfter("Distance:").trim().removeSuffix("miles").toDoubleOrNull() ?: 0.0
                    }
                }
            }
        }
        return ProgressSummary(totalCalories, totalTime, totalDistance)
    }

    // Convert time format to total hours
    private fun parseTimeToHours(time: String): Double {
        val parts = time.split(":").map { it.toIntOrNull() ?: 0 }
        val hours = parts.getOrNull(0) ?: 0
        val minutes = parts.getOrNull(1) ?: 0
        val seconds = parts.getOrNull(2) ?: 0
        return hours + (minutes / 60.0) + (seconds / 3600.0)
    }
}

// Data class to hold overall progress summary
data class ProgressSummary(
    val totalCalories: Int,
    val totalTime: Double,
    val totalDistance: Double
)
