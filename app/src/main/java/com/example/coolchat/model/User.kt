package com.example.coolchat.model

class User(val name: String?, val email: String, val id: String?){
    constructor() : this("", "","")
}
