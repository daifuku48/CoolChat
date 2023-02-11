package com.example.coolchat.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coolchat.R
import com.example.coolchat.databinding.FragmentSignUpTabBinding

class SignUpTabFragment : Fragment() {

    lateinit var binding: FragmentSignUpTabBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpTabBinding.inflate(layoutInflater, container, false)

        binding.loginOrSignButton.setOnClickListener{

        }
        return binding.root
    }

}