package com.example.coolchat.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coolchat.R
import com.example.coolchat.databinding.FragmentLoginTabBinding
import com.example.coolchat.databinding.FragmentSignUpTabBinding

class LoginTabFragment : Fragment() {

    lateinit var binding: FragmentLoginTabBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginTabBinding.inflate(layoutInflater)

        binding.loginOrSignButton.setOnClickListener{

        }
        return binding.root
    }

}