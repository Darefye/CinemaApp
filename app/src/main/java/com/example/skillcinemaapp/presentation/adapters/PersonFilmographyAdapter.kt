package com.example.skillcinemaapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.skillcinemaapp.data.remote.person_dto.PersonFilm
import com.example.skillcinemaapp.databinding.ItemFilmographyDetailBinding


class PersonFilmographyAdapter(
    val onFilmographyMovieItemClick: (PersonFilm) -> Unit
) : ListAdapter<PersonFilm, PersonFilmographyViewHolder>(PersonFilmographyDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonFilmographyViewHolder {
        return PersonFilmographyViewHolder(
            binding = ItemFilmographyDetailBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onFilmographyMovieItemClick = onFilmographyMovieItemClick
        )
    }

    override fun onBindViewHolder(holder: PersonFilmographyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class PersonFilmographyViewHolder(
    val binding: ItemFilmographyDetailBinding, val onFilmographyMovieItemClick: (PersonFilm) -> Unit
) : ViewHolder(binding.root) {
    fun bind(item: PersonFilm) {
        with(binding) {

            filmPicture.isVisible = true
            filmInfo.isVisible = true
            filmTitle.isVisible = true
            if (item.rating != null) {
                filmRating.isVisible = true
                filmRating.text = item.rating
            } else filmRating.isVisible = false

            filmInfo.text = item.description ?: ""
            if (item.posterUri != null) {
                Glide.with(filmPicture.context).load(item.posterUri).centerCrop().into(filmPicture)
            }

            filmTitle.text = item.nameRu ?: item.nameEn ?: ""


            binding.root.setOnClickListener {
                onFilmographyMovieItemClick(item)
            }
        }
    }
}

class PersonFilmographyDiffUtilCallBack : DiffUtil.ItemCallback<PersonFilm>() {
    override fun areItemsTheSame(oldItem: PersonFilm, newItem: PersonFilm): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: PersonFilm, newItem: PersonFilm): Boolean {
        return oldItem == newItem
    }
}


