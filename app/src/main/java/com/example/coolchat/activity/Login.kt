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
    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager2 = findViewById(R.id.view_pager)
        tabLayout.addTab(tabLayout.newTab().setText("SignUp")) //adding tabs to pager in login activity
        tabLayout.addTab(tabLayout.newTab().setText("Login"))

        val fragmentManager = supportFragmentManager
        viewPagerAdapter = ViewPagerAdapter(fragmentManager, lifecycle)
        viewPager2.adapter = viewPagerAdapter

        tabLayout.setOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager2.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
                super.onPageSelected(position)
            }
        })

    }
}