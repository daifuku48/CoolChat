package com.example.coolchat.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coolchat.R

class UserRecyclerAdapter(private val dataSet: ArrayList<User>) :
    RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>() {

    private lateinit var mListener : OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener : OnItemClickListener){
        mListener = listener
    }

    class ViewHolder(view: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(view){
        val userNameTextView: TextView
        val userImageView: ImageView

        init {
            userNameTextView = view.findViewById(R.id.nameItemTextView)
            userImageView = view.findViewById(R.id.iconImageView)
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_recycler_view, parent, false)

        return ViewHolder(view, mListener)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.userImageView.setImageResource(dataSet[position].icon)
        holder.userNameTextView.text = dataSet[position].name
    }
}

