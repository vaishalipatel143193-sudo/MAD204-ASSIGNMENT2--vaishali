package com.example.assignment2_vaishali

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment2_vaishali.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val TAG = "DashboardActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Prefs.init(applicationContext)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val displayName = Prefs.name ?: Prefs.email ?: "User"
        binding.tvWelcome.text = "Welcome, $displayName"

        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        binding.btnCountries.setOnClickListener {
            startActivity(Intent(this, CountriesListActivity::class.java))
        }
        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        binding.btnLogout.setOnClickListener {
            Prefs.clearAll()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart(); Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume(); Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause(); Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop(); Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy(); Log.d(TAG, "onDestroy")
    }
}