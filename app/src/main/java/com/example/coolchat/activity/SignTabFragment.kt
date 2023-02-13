package com.example.coolchat.activity

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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignTabFragment : Fragment() {

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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}