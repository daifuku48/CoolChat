package com.example.coolchat.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.*

class Message(var text: String,
              var name: String?,
              var imageUri: String,
              var senderId: String,
              var recipientId: String,
              var isLeft: Boolean,
              ) {
    constructor() : this("", "", "", "", "", false)
}