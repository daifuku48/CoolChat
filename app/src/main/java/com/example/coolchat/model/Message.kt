package com.example.coolchat.model

class Message(var text: String, var name: String?, var imageUri: String) {
    constructor() : this("", "", "")
}