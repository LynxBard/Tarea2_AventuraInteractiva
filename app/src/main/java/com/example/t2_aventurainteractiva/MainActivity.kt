package com.example.t2_aventurainteractiva

import android.content.Context
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Step 1: Read SharedPreferences and apply theme
        val sharedPreferences = getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        setContentView(R.layout.activity_main)

        // Step 2: Configure the switch
        val themeSwitch = findViewById<Switch>(R.id.theme_switch)
        themeSwitch.isChecked = isDarkMode

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Step 3: Save the preference and recreate the activity
            with(sharedPreferences.edit()) {
                putBoolean("dark_mode", isChecked)
                apply()
            }
            recreate() // This will restart the activity, and onCreate will apply the new theme
        }

        // Load the fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.motherboard_container, MotherboardFragment())
                .commit()
        }
    }
}
