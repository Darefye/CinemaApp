package com.example.skillcinemaapp.ui.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.*
import com.example.skillcinemaapp.R
import com.example.skillcinemaapp.databinding.FragmentCollectionBinding
import com.example.skillcinemaapp.presentation.adapters.FragmentCollectionAdapter
import com.example.skillcinemaapp.ui.loader.LoaderFragment
import dagger.hilt.android.AndroidEntryPoint
import me.relex.circleindicator.CircleIndicator3

@AndroidEntryPoint
class CollectionFragment : Fragment() {

    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!

    private lateinit var fragmentCollectionAdapter: FragmentCollectionAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var indicator: CircleIndicator3
    private lateinit var skipButton: AppCompatButton
    private val pagerCallBack = object : OnPageChangeCallback() {

        var currentPage = 0
        var isSettled = false

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            if (state == SCROLL_STATE_DRAGGING) {
                isSettled = false
            }
            if (state == SCROLL_STATE_SETTLING) {
                isSettled = true
            }

        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            currentPage = position + 1
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentCollectionAdapter = FragmentCollectionAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = fragmentCollectionAdapter
        indicator = binding.circleIndicator
        skipButton = binding.skipOnboarding

        skipButton.setOnClickListener {
            onBoardingFinished()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_collection_layout, LoaderFragment.newInstance()).commit()
        }

        indicator.setViewPager(viewPager)
        viewPager.registerOnPageChangeCallback(pagerCallBack)
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CollectionFragment()
    }
}
