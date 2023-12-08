package com.example.androidadvance

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class NotificationPagerAdapter(fragmentActivity: NotificationFragment) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllFragment()
            1 -> StatusFragment()
            2 -> InfoFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}
