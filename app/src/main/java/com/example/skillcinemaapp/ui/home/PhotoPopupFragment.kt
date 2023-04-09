package com.example.skillcinemaapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.skillcinemaapp.R
import com.example.skillcinemaapp.databinding.PhotoPopupBinding
import com.example.skillcinemaapp.presentation.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class PhotoPopupFragment : Fragment() {

    private var _binding: PhotoPopupBinding? = null
    private val binding get() = _binding!!

    private lateinit var photoPlaceholder: AppCompatImageView

    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PhotoPopupBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoPlaceholder = binding.photoPlaceholder

        photoPlaceholder.setOnClickListener {
            findNavController().navigate(R.id.action_photoPopupFragment_to_personFragment)
        }


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            movieViewModel.person.collectLatest {
                if (it != null) {
                    Glide
                        .with(photoPlaceholder.context)
                        .load(it.posterUrl)
                        .into(photoPlaceholder)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

