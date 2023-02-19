package com.example.coolchat.model

import android.widget.ImageView
import com.example.coolchat.R

class User(var name: String?, var email: String, var id: String?, var icon: Int){
    constructor() : this("", "","", R.drawable.zoro)
}
