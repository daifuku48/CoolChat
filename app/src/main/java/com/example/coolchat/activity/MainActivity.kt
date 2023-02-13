package com.example.coolchat.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import com.example.coolchat.R
import com.example.coolchat.databinding.ActivityMainBinding
import com.example.coolchat.model.Message
import com.example.coolchat.model.MessageCustomAdapter
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    lateinit var adapter: MessageCustomAdapter
    lateinit var database: Firebase
    lateinit var messagesDatabaseReference: DatabaseReference
    lateinit var messageChildEventListener: ChildEventListener
    val username = "user"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val database = Firebase.database
        val messagesDatabaseReference = database.getReference("messages")
        binding?.progressBarOfSending?.visibility = View.INVISIBLE
        val listOfMessage = ArrayList<Message>()
        adapter = MessageCustomAdapter(this, R.layout.item_message, listOfMessage)
        binding?.messageListView?.adapter = adapter
        binding?.messageButton?.isEnabled = false
        binding?.textOfMessageEditText?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding?.messageButton?.isEnabled = p0.toString().trim().isNotEmpty()
            }
            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding?.textOfMessageEditText?.filters = arrayOf<InputFilter>(
            InputFilter.LengthFilter(
                1000
            )
        )

        binding?.messageButton?.setOnClickListener {
            val text = binding?.textOfMessageEditText?.text.toString()
            val message = Message(text, username, 0)
            messagesDatabaseReference.push().setValue(message)
            binding?.textOfMessageEditText?.text?.clear()
        }

        binding?.sendPhotoButton?.setOnClickListener{

        }


        messageChildEventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(Message::class.java)
                adapter.add(message)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        messagesDatabaseReference.addChildEventListener(messageChildEventListener)
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}