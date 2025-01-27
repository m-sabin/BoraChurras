package com.example.borachurras

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.borachurras.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numofadlt = intent.getIntExtra("NumAdult", 0)
        val numofchild = intent.getIntExtra("NumChild", 0)
        val timing = intent.getIntExtra("Time", 0)

        val meatKg = calcMeat(numofadlt, numofchild, timing)
        val beerLatas = calcBeer(numofadlt, timing)
        val refrilitros = calcRefri(numofadlt, numofchild, timing)

        binding.tvQtdMeat.text = "%.2f".format(meatKg)
        binding.tvQtdBear.text = beerLatas.toString()
        binding.tvQtdRefri.text = "%.2f".format(refrilitros)

        binding.btnRecalcular.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun calcMeat(adult: Int, child: Int, timing: Int): Double {
        val consumAdult = if (timing > 4) 0.6 else 0.4
        val consumChild = consumAdult / 2
        return (adult * consumAdult * timing) + (child * consumChild * timing)
    }

    private fun calcBeer(adult: Int, timing: Int): Int {
        val consumAdult = if (timing > 4) 7 else 5
        return adult * consumAdult * timing
    }

    private fun calcRefri(adult: Int, child: Int, timing: Int): Double {
        val consumAdult = 0.5
        val consumChild = 0.3
        return (adult * consumAdult * timing) + (child * consumChild * timing)
    }
}