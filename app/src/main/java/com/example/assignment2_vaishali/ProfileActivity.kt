package com.example.assignment2_vaishali

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment2_vaishali.databinding.ActivityProfileBinding
import com.google.android.material.textfield.TextInputEditText

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val TAG = "ProfileActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Prefs.init(applicationContext)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadProfile()

        binding.btnEditProfile.setOnClickListener {
            showEditDialog()
        }
    }

    private fun loadProfile() {
        binding.tvName.text = Prefs.name ?: "N/A"
        binding.tvEmail.text = Prefs.email ?: "N/A"
        binding.tvAge.text = (Prefs.age.takeIf { it > 0 }?.toString() ?: "N/A")
        binding.tvProgram.text = Prefs.program ?: "N/A"
    }

    private fun showEditDialog() {
        val v = layoutInflater.inflate(R.layout.dialog_edit_profile, null)
        val etName = v.findViewById<TextInputEditText>(R.id.etDialogName)
        val etProgram = v.findViewById<TextInputEditText>(R.id.etDialogProgram)
        val etAge = v.findViewById<TextInputEditText>(R.id.etDialogAge)

        etName?.setText(Prefs.name)
        etProgram?.setText(Prefs.program)
        etAge?.setText(if (Prefs.age > 0) Prefs.age.toString() else "")

        AlertDialog.Builder(this)
            .setTitle("Edit Profile")
            .setView(v)
            .setPositiveButton("Save") { _, _ ->
                val name = etName?.text.toString().trim()
                val program = etProgram?.text.toString().trim()
                val age = try { etAge?.text.toString().trim().toInt() } catch (e: Exception) { -1 }

                if (name.isNotEmpty()) Prefs.name = name
                if (program.isNotEmpty()) Prefs.program = program
                if (age > 0) Prefs.age = age

                loadProfile()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onStart() { super.onStart(); Log.d(TAG,"onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG,"onResume") }
    override fun onPause() { super.onPause(); Log.d(TAG,"onPause") }
    override fun onStop() { super.onStop(); Log.d(TAG,"onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG,"onDestroy") }
}