package com.example.chatapp.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.chatapp.R
import com.example.chatapp.repository.ChatUser
import com.example.chatapp.utils.getChatRoot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.ktx.Firebase

class ChatScreenFragment: DialogFragment(R.layout.chat_fragment) {
    // current user
    private lateinit var currentUser: ChatUser

    // user which will receive the chat
    private lateinit var chatWith : ChatUser

    val chatHeadRef = FirebaseDatabase.getInstance().reference.child("chats")

    private lateinit var chatQuery : Query

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readArguments()
        chatQuery = chatHeadRef.child(
            getChatRoot(currentUser.uid, chatWith.uid)
        ).limitToLast(50)
        setUpToolbar()
        setUpSendMsgView()
        attachRecyclerViewAdapter()
    }

    private fun attachRecyclerViewAdapter() {

    }

    private fun setUpSendMsgView() {

    }

    private fun setUpToolbar() {

    }

    private fun readArguments() {

    }


}