package com.example.coolchat.model

class Message(var text: String, var name: String?, var imageUrl: Int) {
    constructor() : this("", "", 0)
}