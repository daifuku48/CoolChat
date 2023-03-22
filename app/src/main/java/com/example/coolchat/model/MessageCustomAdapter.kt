package com.example.coolchat.model

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.coolchat.R

class MessageCustomAdapter(private val context: Activity, private val listOfMessage: ArrayList<Message?>)
    : ArrayAdapter<Message>(context, R.layout.item_message_right, listOfMessage) {


    private val MESSAGE_RIGHT = R.layout.item_message_right

    private val MESSAGE_LEFT = R.layout.item_message_left
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val messageItem = getItem(position)
        val view = if (messageItem?.isLeft == true)
            context.layoutInflater.inflate(MESSAGE_LEFT, null)
        else context.layoutInflater.inflate(MESSAGE_RIGHT, null)

        val textOfMessage = view.findViewById<TextView?>(R.id.text_message)
        val imageViewOfMessage = view.findViewById<ImageView?>(R.id.photoImageView)
        val dataTimeTextView = view.findViewById<TextView?>(R.id.dataTimeTextView)

        if (listOfMessage.isNotEmpty()) {
            textOfMessage.text = listOfMessage[position]?.text
            dataTimeTextView.text = "time"
            if (listOfMessage[position]?.imageUri != "") {
                Glide.with(imageViewOfMessage.context)
                    .load(listOfMessage[position]?.imageUri)
                    .into(imageViewOfMessage)
                imageViewOfMessage.visibility = View.VISIBLE
            }
        }
        return view
    }
}