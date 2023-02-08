package com.example.coolchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.coolchat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.messageButton.isEnabled = false
        binding.textOfMessageEditText.addTextChangedListener {
            binding.messageButton.isEnabled = it.toString().isNotEmpty()
        }
        binding.messageButton.setOnClickListener{
            Toast.makeText(this, "button is active", Toast.LENGTH_LONG).show()
        }

    }
}