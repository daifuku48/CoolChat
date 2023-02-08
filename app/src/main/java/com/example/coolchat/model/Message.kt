package com.example.coolchat.model

class Message(val text: String, val name: String, val imageUrl: Int) {
    constructor() : this("", "", 0)
}