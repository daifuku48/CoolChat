package com.example.coolchat.activity

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.coolchat.databinding.FragmentLoginTabBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginTabFragment : Fragment() {

    private lateinit var auth: FirebaseAuth //variable for authorization
    var binding: FragmentLoginTabBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginTabBinding.inflate(layoutInflater)
        auth = Firebase.auth
        binding?.loginOrSignButton?.setOnClickListener{
            //sing in in sign in fragment
            auth.signInWithEmailAndPassword(binding?.emailEditText?.text.toString(), binding?.passwordEditText?.text.toString())
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Sign in", "signInWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Sign in", "signInWithEmail:failure", task.exception)
                        Toast.makeText(activity?.baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }
                }

        }

        //writing no more 80 letters in email edit text
        binding?.emailEditText?.filters = arrayOf<InputFilter>(
            InputFilter.LengthFilter(
                80
            )
        )

        //writing no more 80 letters in password edit text
        binding?.passwordEditText?.filters = arrayOf<InputFilter>(
            InputFilter.LengthFilter(
                25
            )
        )

        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){

        }
    }

    private fun updateUI(user: FirebaseUser?)
    {
        val intentToMainActivity = Intent(requireContext(), MainActivity::class.java)
        startActivity(intentToMainActivity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}