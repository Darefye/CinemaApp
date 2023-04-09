package com.example.skillcinemaapp.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.skillcinemaapp.ui.home.GallerySingleFragment
import com.example.skillcinemaapp.ui.onboarding.OnboardingMainFragment


class FragmentGalleryCollectionAdapter(
    fragment: Fragment, private val gallerySize: Int
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = gallerySize

    override fun createFragment(position: Int): Fragment =
        GallerySingleFragment.newInstance(position)

}