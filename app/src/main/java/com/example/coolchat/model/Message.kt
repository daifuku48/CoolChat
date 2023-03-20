package com.example.coolchat.model

class Message(var text: String, var name: String?, var imageUri: String, var senderId: String, var recipientId: String, var isLeft: Boolean) {
    constructor() : this("", "", "", "", "", false)
}