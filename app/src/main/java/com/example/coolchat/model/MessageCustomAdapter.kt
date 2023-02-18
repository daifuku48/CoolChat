package com.example.coolchat.model

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.coolchat.R

class MessageCustomAdapter(private val context: Activity, private val resource: Int, private val listOfMessage: List<Message?>)
    : ArrayAdapter<Message>(context, R.layout.item_message, listOfMessage) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = context.layoutInflater.inflate(resource, parent, false)

        val textOfMessage = view.findViewById<TextView?>(R.id.nameTextView)
        val nameTextViewOfMessage = view.findViewById<TextView?>(R.id.textTextView)
        val imageViewOfMessage = view.findViewById<ImageView?>(R.id.photoImageView)


        if (listOfMessage.isNotEmpty())
        {
            textOfMessage.text = listOfMessage[position]?.text
            nameTextViewOfMessage.text = listOfMessage[position]?.name
            Glide.with(imageViewOfMessage.context)
                .load(listOfMessage[position]?.imageUri)
                .into(imageViewOfMessage)
            if (listOfMessage[position]?.imageUri != "")
            {
                textOfMessage.visibility = View.INVISIBLE
            }
        }


        return view
    }
}