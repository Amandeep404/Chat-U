package com.example.chatapp.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chatapp.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_chat_home.*

class ChatLandingFragment : Fragment(R.layout.fragment_chat_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = ChatViewPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text =  TAB_TITLES[position]
        }.attach()
    }
}


