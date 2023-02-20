package com.example.coolchat.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coolchat.databinding.ActivityUserListBinding
import com.example.coolchat.model.User
import com.example.coolchat.model.UserRecyclerAdapter
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth


class UserListActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var usersDataBaseReference: DatabaseReference
    private  lateinit var usersChildEventListener: ChildEventListener
    private var binding: ActivityUserListBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var usersArrayList: ArrayList<User>
    private lateinit var usersAdapter: UserRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        buildRecyclerView()
        initializeFirebaseDatabase()
        auth = Firebase.auth
    }


    private fun buildRecyclerView()
    {
        val userLayoutManager = LinearLayoutManager(this)
        binding?.userListRecyclerView?.layoutManager = userLayoutManager
        usersArrayList = ArrayList()
        usersAdapter = UserRecyclerAdapter(usersArrayList)
        binding?.userListRecyclerView?.adapter = usersAdapter

        binding?.userListRecyclerView?.addItemDecoration(
            DividerItemDecoration(
                binding?.userListRecyclerView?.context, DividerItemDecoration.VERTICAL
            )
        )
        usersAdapter.setOnItemClickListener(object : UserRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {

            }
        })

        binding?.userListRecyclerView?.adapter = usersAdapter
    }

    private fun initializeFirebaseDatabase(){
        database = Firebase.database
        usersDataBaseReference = FirebaseDatabase.getInstance().reference.child("users")
        usersChildEventListener = object: ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val user = snapshot.getValue(User::class.java)
                Toast.makeText(applicationContext,"there is a obj" + user?.name, Toast.LENGTH_LONG).show()

                if (user != null) {
                    usersArrayList.add(user)
                    usersAdapter.notifyItemInserted(usersArrayList.size-1)
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