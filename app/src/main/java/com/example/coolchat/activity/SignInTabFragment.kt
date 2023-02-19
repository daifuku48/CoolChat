package com.example.coolchat.activity

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.coolchat.R
import com.example.coolchat.databinding.FragmentSignUpTabBinding
import com.example.coolchat.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignInTabFragment : Fragment() {

    private lateinit var auth: FirebaseAuth //variable for authorization
    var binding: FragmentSignUpTabBinding? = null
    lateinit var usersDatabaseReference: DatabaseReference
    lateinit var usersChildEventListener: ChildEventListener
    lateinit var database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpTabBinding.inflate(layoutInflater)
        auth = Firebase.auth
        database = Firebase.database
        usersDatabaseReference = database.getReference("users")

        binding?.loginOrSignButton?.setOnClickListener{
            //authorization in sign in fragment
            Log.d("d", "button is clicked")
            auth.createUserWithEmailAndPassword(binding?.emailEditText?.text.toString(), binding?.passwordEditText?.text.toString())
                .addOnCompleteListener(requireActivity()) { task ->

                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("d", "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("d", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            activity?.baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
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

        binding?.emailEditText?.filters = arrayOf<InputFilter>(
            LengthFilter(80)
        )

        binding?.passwordEditText?.filters = arrayOf<InputFilter>(
            LengthFilter(25)
        )

        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){

        }
    }

    private fun updateUI(user : FirebaseUser?){
        createUser(user)
        val intentToMainActivity = Intent(requireContext(), UserListActivity::class.java)
        intentToMainActivity.apply {
            putExtra("userName", binding?.nameEditText?.text.toString())
        }
        startActivity(intentToMainActivity)
    }

    private fun createUser(user : FirebaseUser?)
    {
        val userCreate = User(binding?.nameEditText?.text.toString(), user?.email.toString(), user?.uid.toString(), R.drawable.zoro)
        usersDatabaseReference.push().setValue(userCreate)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}