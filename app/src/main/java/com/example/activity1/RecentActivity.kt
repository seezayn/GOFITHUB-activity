package com.example.activity1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import java.io.File

class RecentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recent)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Loading activities from file
        val activities = loadActivitiesFromFile()

        val listView: ListView = findViewById(R.id.listViewRecentActivities)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, activities)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedActivity = activities[position]
            Toast.makeText(this, "Selected: $selectedActivity", Toast.LENGTH_SHORT).show()
        }

        // Setting Goals button
        val btnGoals = findViewById<MaterialButton>(R.id.btnGoals)
        btnGoals.setOnClickListener {
            val intent = Intent(this, Goals::class.java)
            startActivity(intent)
        }
    }

    // Load activities from the activities.txt file
    private fun loadActivitiesFromFile(): List<String> {
        val fileName = "activities.txt"
        val file = File(filesDir, fileName)

        return if (file.exists()) {
            file.readLines()
        } else {
            Toast.makeText(this, "No recent activities found.", Toast.LENGTH_SHORT).show()
            emptyList()
        }
    }
}
