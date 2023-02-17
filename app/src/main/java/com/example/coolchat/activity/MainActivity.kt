package com.example.coolchat.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.coolchat.R
import com.example.coolchat.databinding.ActivityMainBinding
import com.example.coolchat.model.Message
import com.example.coolchat.model.MessageCustomAdapter
import com.example.coolchat.model.User
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(){
    private lateinit var auth: FirebaseAuth
    var binding: ActivityMainBinding? = null
    lateinit var adapter: MessageCustomAdapter
    lateinit var database: FirebaseDatabase

    lateinit var messagesDatabaseReference: DatabaseReference
    lateinit var messageChildEventListener: ChildEventListener
    lateinit var usersDatabaseReference: DatabaseReference
    lateinit var usersChildEventListener: ChildEventListener
    private var username: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val intent : Intent? = intent
        username = intent?.getStringExtra("userName")

        auth = Firebase.auth
        database = Firebase.database
        messagesDatabaseReference = database.getReference("messages")
        usersDatabaseReference = database.getReference("users")
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

        usersChildEventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?){
                val user = snapshot.getValue(User::class.java)

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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activity_main_driver, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.menu_log_out -> {
                Log.d("log out", "user is out")
                auth.signOut()
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}