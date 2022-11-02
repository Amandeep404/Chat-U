package com.example.chatapp.contacts

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatapp.R
import com.example.chatapp.repository.ChatUser

class ContactsViewHolder(
    itemView : View
    ): RecyclerView.ViewHolder(itemView) {
        private val name : TextView = itemView.findViewById(R.id.contactTextView)
        private val pic : ImageView = itemView.findViewById(R.id.ivUserImage)

    fun bind(user : ChatUser){
        name.text = user.displayName
        user.photoUrl?.let{ photourl ->
            if (photourl.isNotEmpty()){
                Glide
                    .with(itemView)
                    .load(photourl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_baseline_person_24)
                    .into(pic)

            }

        }
    }
}