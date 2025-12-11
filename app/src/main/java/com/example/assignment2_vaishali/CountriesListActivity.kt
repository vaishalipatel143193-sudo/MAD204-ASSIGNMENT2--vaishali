package com.example.assignment2_vaishali

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_vaishali.databinding.ActivityCountriesListBinding
import com.google.android.material.snackbar.Snackbar

class CountriesListActivity : AppCompatActivity(), CountriesAdapter.OnItemClickListener {
    private lateinit var binding: ActivityCountriesListBinding
    private lateinit var adapter: CountriesAdapter
    private val countries = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Prefs.init(applicationContext)
        binding = ActivityCountriesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Fill sample 10+ countries
        countries.addAll(listOf(
            "India","United States","Canada","United Kingdom","Germany",
            "France","Australia","Japan","China","Brazil","South Africa","Mexico"
        ))

        adapter = CountriesAdapter(countries, this)
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter

        updateEmptyState()

        binding.btnSort.setOnClickListener {
            countries.sort()
            adapter.notifyDataSetChanged()
            updateEmptyState()
        }

        // Long press handled in adapter -> delete with Snackbar undo
        // Add swipe to delete via ItemTouchHelper
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false
            override fun onSwiped(vh: RecyclerView.ViewHolder, direction: Int) {
                val pos = vh.bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) deleteWithUndo(pos)
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recycler)
    }

    private fun deleteWithUndo(position: Int) {
        val removed = countries.removeAt(position)
        adapter.notifyItemRemoved(position)
        updateEmptyState()
        Snackbar.make(binding.root, "Deleted: $removed", Snackbar.LENGTH_LONG)
            .setAction("UNDO") {
                countries.add(position, removed)
                adapter.notifyItemInserted(position)
                updateEmptyState()
            }.show()
    }

    private fun updateEmptyState() {
        binding.tvEmpty.visibility = if (countries.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onItemClick(country: String) {
        Toast.makeText(this, "You selected: $country", Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(position: Int) {
        deleteWithUndo(position)
    }
}