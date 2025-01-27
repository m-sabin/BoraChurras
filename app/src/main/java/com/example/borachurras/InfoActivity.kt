package com.example.borachurras

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.borachurras.databinding.ActivityInfoBinding
import com.google.android.material.snackbar.Snackbar

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener {
            val numofadult = binding.tieNumAdult.text.toString()
            val numofchild = binding.tieNumChild.text.toString()
            val timing = binding.tieTime.text.toString()

            if (numofadult.isEmpty() || numofchild.isEmpty() || timing.isEmpty()) {
                Snackbar.make(
                    binding.tieNumAdult,
                    "Por favor, preencha todos os campos",
                    Snackbar.LENGTH_LONG
                ).show()
                return@setOnClickListener
            } else {
                val numofadultInt = numofadult.toIntOrNull()
                val numofchildInt = numofchild.toIntOrNull()
                val timingInt = timing.toIntOrNull()

                if (numofadultInt == null || numofchildInt == null || timingInt == null) {
                    Snackbar.make(
                        binding.tieNumAdult,
                        "Por favor, insira núeros válidos em todos os campos",
                        Snackbar.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("NumAdult", numofadultInt)
                intent.putExtra("NumChild", numofchildInt)
                intent.putExtra("Time", timingInt)
                startActivity(intent)

            }
        }
    }
}