package com.example.skillcinemaapp.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.skillcinemaapp.ui.onboarding.OnboardingMainFragment


class FragmentCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        OnboardingMainFragment.newInstance(position)
}