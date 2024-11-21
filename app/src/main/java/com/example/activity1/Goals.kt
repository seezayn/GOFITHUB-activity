package com.example.activity1

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class Goals : AppCompatActivity() {

    private lateinit var goalsListView: ListView
    private lateinit var goalInput: EditText
    private lateinit var addGoalButton: Button
    private lateinit var deleteGoalsButton: Button
    private lateinit var goalsAdapter: ArrayAdapter<String>
    private val goalsList = mutableListOf<String>()
    private val fileName = "goals.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_goals)

        val rootView = findViewById<View>(R.id.main)
        if (rootView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        } else {
            Toast.makeText(this, "Root view not found. Check your layout.", Toast.LENGTH_SHORT).show()
        }

        goalsListView = findViewById(R.id.lvGoals)
        goalInput = findViewById(R.id.etGoalInput)
        addGoalButton = findViewById(R.id.btnSubmitGoal)
        deleteGoalsButton = findViewById(R.id.btnDeleteGoals)

        // Loading goals
        loadGoals()
        goalsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, goalsList)
        goalsListView.adapter = goalsAdapter
        goalsListView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        // Adding Goal Button
        addGoalButton.setOnClickListener {
            val newGoal = goalInput.text.toString().trim()
            if (newGoal.isNotEmpty()) {
                goalsList.add(newGoal)
                goalsAdapter.notifyDataSetChanged()
                saveGoals()
                goalInput.text.clear()
                Toast.makeText(this, "Goal added!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a goal", Toast.LENGTH_SHORT).show()
            }
        }

        // Delete Goals
        deleteGoalsButton.setOnClickListener {
            val checkedItems = goalsListView.checkedItemPositions
            for (i in goalsListView.count - 1 downTo 0) {
                if (checkedItems[i]) {
                    goalsList.removeAt(i)
                }
            }
            goalsAdapter.notifyDataSetChanged()
            saveGoals()
            Toast.makeText(this, "Checked goals deleted!", Toast.LENGTH_SHORT).show()
        }
    }

    // Loading goals from file
    private fun loadGoals() {
        val file = File(filesDir, fileName)
        if (file.exists()) {
            val savedGoals = file.readLines()
            goalsList.addAll(savedGoals)
        }
    }

    // Saving goals
    private fun saveGoals() {
        val file = File(filesDir, fileName)
        file.writeText(goalsList.joinToString("\n"))
    }
}
