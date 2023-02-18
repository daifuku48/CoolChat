package com.example.coolchat.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coolchat.databinding.ActivityUserListBinding

class UserListActivity : AppCompatActivity() {

    private var binding: ActivityUserListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}