package com.example.coolchat.activity

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.coolchat.R
import com.example.coolchat.databinding.ActivityLoginBinding
import com.google.android.material.tabs.TabLayout

class Login : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("SignUp")) //adding tabs to pager in login activity
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Login"))

        val fragmentManager = supportFragmentManager
        viewPagerAdapter = ViewPagerAdapter(fragmentManager, lifecycle)
        binding.viewPager.adapter = viewPagerAdapter

        binding.tabLayout.setOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
                super.onPageSelected(position)
            }
        })
    }
}