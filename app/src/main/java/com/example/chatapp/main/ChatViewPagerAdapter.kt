package com.example.chatapp.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chatapp.contacts.ContactsFragment

val TAB_TITLES = arrayOf(
    "Contacts",
    "Calls",
    "Profile"
)

class ChatViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){

    override fun getItemCount(): Int {
        return TAB_TITLES.size
    }

    override fun createFragment(position: Int) = when(position) {
        0 ->{
            ContactsFragment()
        }
        1 ->{
            PlaceHolderFragment()
        }
        2 ->{
            UserProfileFragment()
        }

        else -> ContactsFragment()

    }

}
