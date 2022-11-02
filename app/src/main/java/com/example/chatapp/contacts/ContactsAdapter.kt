package com.example.chatapp.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import com.example.chatapp.R
import com.example.chatapp.repository.ChatUser
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class ContactsAdapter(
    private val options : FirebaseRecyclerOptions<ChatUser>,
    private val onItemClickListener : OnItemClickListener
): FirebaseRecyclerAdapter<ChatUser, ContactsViewHolder>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val holder = ContactsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_contacts, parent, false)
        )
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(
                options.snapshots[holder.adapterPosition]
            )
        }
        return  holder
    }
    interface OnItemClickListener{
        fun onItemClick(chatWith : ChatUser)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int, model: ChatUser) {
       holder.bind(model)
    }

    override fun onDataChanged() {
        //TODO If there are no chat messages, show a view that invites the user to add a message.
        // mBinding.emptyTextView.setVisibility(if (itemCount == 0) View.VISIBLE else View.GONE)
    }
}