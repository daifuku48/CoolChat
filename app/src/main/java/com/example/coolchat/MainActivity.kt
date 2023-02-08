package com.example.coolchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.coolchat.databinding.ActivityMainBinding
import com.example.coolchat.model.Message
import com.example.coolchat.model.MessageCustomAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MessageCustomAdapter
    lateinit var database: Firebase
    lateinit var messagesDatabaseReference: DatabaseReference

    val username = "user"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database = Firebase.database
        val messagesDatabaseReference = database.getReference("messages")
        messagesDatabaseReference.setValue("aboba")
        binding.progressBarOfSending.visibility = View.INVISIBLE
        val listOfMessage = ArrayList<Message>()
        adapter = MessageCustomAdapter(this, R.layout.item_message, listOfMessage)
        binding.messageButton.isEnabled = false
        binding.textOfMessageEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.messageButton.isEnabled = p0.toString().trim().isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.textOfMessageEditText.filters = arrayOf<InputFilter>(
            InputFilter.LengthFilter(
                1000
            )
        )

        binding.messageButton.setOnClickListener {
            binding.textOfMessageEditText.text?.clear()
            Toast.makeText(this, "button is active", Toast.LENGTH_LONG).show()
        }
        binding.sendPhotoButton.setOnClickListener{

        }
    }
}