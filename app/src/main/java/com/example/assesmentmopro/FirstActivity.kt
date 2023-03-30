package com.example.assesmentmopro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import com.example.assesmentmopro.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val name = binding.editTextTextPersonName4.text.toString()

            // Validasi input nama
            val regex = "^[a-zA-Z]{1,8}\$".toRegex()
            if (name.matches(regex)) {
                val intent = Intent(this@FirstActivity, MainActivity::class.java)
                intent.putExtra(MainActivity.NAME, name)
                startActivity(intent)
            } else {
                binding.editTextTextPersonName4.error = "Input nama tidak valid/tidak boleh lebih dari 8 karakter"
            }
        }

    }
}
