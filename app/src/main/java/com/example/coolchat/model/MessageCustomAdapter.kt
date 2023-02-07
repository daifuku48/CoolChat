package com.example.coolchat.model

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.coolchat.R

class MessageCustomAdapter(private val context: Activity, private val description: Array<String>, private val listOfMessage: List<Message>)
    : ArrayAdapter<Message>(context, R.layout.item_message) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = context.layoutInflater.inflate(R.layout.item_message, parent, false)

        val textOfMessage = view.findViewById<TextView>(R.id.nameTextView)
        val nameTextViewOfMessage = view.findViewById<TextView>(R.id.nameTextView)
        val imageViewOfMessage = view.findViewById<ImageView>(R.id.photoImageView)

        textOfMessage.text = listOfMessage[position].text
        nameTextViewOfMessage.text = listOfMessage[position].name
        imageViewOfMessage.(listOfMessage[position].imageUrl)

        return super.getView(position, convertView, parent)
    }
}