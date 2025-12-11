package com.example.assignment2_vaishali

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment2_vaishali.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Prefs.init(applicationContext)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Load saved settings
        binding.switchDarkMode.isChecked = Prefs.darkMode
        binding.switchNotifications.isChecked = Prefs.notifications

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            Prefs.darkMode = isChecked
            // Optionally apply theme change (requires recreate & theming handling)
        }
        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            Prefs.notifications = isChecked
        }

        // Example checkboxes (if you want)
        binding.checkboxExample.isChecked = false
        binding.checkboxExample.setOnCheckedChangeListener { _, _ ->
            // persist or handle as needed
        }
    }
}