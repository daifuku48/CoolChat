package com.example.coolchat.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coolchat.databinding.ActivityUserListBinding
import com.example.coolchat.model.User
import com.example.coolchat.model.UserRecyclerAdapter
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserListActivity : AppCompatActivity() {

    private lateinit var database : FirebaseDatabase
    private lateinit var usersDataBaseReference : DatabaseReference
    private  lateinit var usersChildEventListener : ChildEventListener
    private var binding: ActivityUserListBinding? = null

    lateinit var usersArrayList : ArrayList<User>
    lateinit var usersAdapter: UserRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initializeFirebaseDatabase()
        buildRecyclerView()

    }


    private fun buildRecyclerView()
    {
        val userLayoutManager = LinearLayoutManager(this)
        binding?.userListRecyclerView?.layoutManager = userLayoutManager
        usersArrayList = ArrayList()
        usersAdapter = UserRecyclerAdapter(usersArrayList)
    }

    private fun initializeFirebaseDatabase(){
        database = Firebase.database
        usersDataBaseReference = database.getReference("users")
        usersChildEventListener = object: ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val user = snapshot.getValue(User::class.java)
                if (user != null)
                {
                    usersArrayList.add(user)
                    usersAdapter.notifyItemChanged(usersAdapter.itemCount-1)
                }
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
        usersDataBaseReference.addChildEventListener(usersChildEventListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}