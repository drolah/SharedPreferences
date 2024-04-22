package com.example.sharedpreferences

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.SharedPreferences
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var userName: EditText
    private lateinit var saveButton: Button
    private lateinit var displayUserName: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userName = findViewById(R.id.userName)
        saveButton = findViewById(R.id.saveButton)
        displayUserName = findViewById(R.id.displayUser)
        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

        saveButton.setOnClickListener {
            val username = userName.text.toString()
            saveUsername(username)
        }

        // Retrieve and display the initial saved username
        val savedUsername = getUsername()
        displayUserName.text = "Saved username: $savedUsername"
    }

    private fun saveUsername(username: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.apply()

        // Update TextView with the new username
        displayUserName.text = "Saved username: $username"
    }

    private fun getUsername(): String {
        return sharedPreferences.getString("username", "No username found") ?: "No username found"
    }
}