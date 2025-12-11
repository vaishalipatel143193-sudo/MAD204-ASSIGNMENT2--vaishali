package com.example.assignment2_vaishali

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment2_vaishali.databinding.ActivityRegistrationBinding
import com.google.android.material.snackbar.Snackbar

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Prefs.init(applicationContext)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnRegister.setOnClickListener {
            val fullName = binding.etFullName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val ageStr = binding.etAge.text.toString().trim()
            val program = binding.etProgram.text.toString().trim()

            if (fullName.isEmpty() || email.isEmpty() || ageStr.isEmpty() || program.isEmpty()) {
                Snackbar.make(binding.root, "All fields are required", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age = try {
                ageStr.toInt()
            } catch (e: Exception) {
                -1
            }

            if (age <= 0) {
                binding.etAge.error = "Age must be > 0"
                return@setOnClickListener
            }

            // Save to SharedPreferences
            Prefs.name = fullName
            Prefs.email = email
            Prefs.age = age
            Prefs.program = program

            Snackbar.make(binding.root, "Registered. Please login.", Snackbar.LENGTH_SHORT).show()

            finish() // return to login
        }

    }
}