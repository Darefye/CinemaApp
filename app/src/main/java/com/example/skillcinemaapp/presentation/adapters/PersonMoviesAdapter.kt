package com.example.skillcinemaapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.skillcinemaapp.data.remote.person_dto.PersonFilm
import com.example.skillcinemaapp.databinding.ItemFilmBinding
import com.example.skillcinemaapp.databinding.ShowAllItemBinding


class PersonMoviesAdapter(
    val onPersonMovieItemClick: (PersonFilm) -> Unit, val onShowAllPersonMoviesClick: (View) -> Unit
) : ListAdapter<PersonFilm, ViewHolder>(PersonMovieDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == ITEM_MOVIES) {
            PersonMovieViewHolder(
                binding = ItemFilmBinding.inflate(
                    layoutInflater, parent, false
                ), onPersonMovieItemClick = onPersonMovieItemClick
            )
        } else ShowAllViewHolder(
            binding = ShowAllItemBinding.inflate(
                layoutInflater, parent, false
            ), onShowAllItemClick = onShowAllPersonMoviesClick
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            if (getItemViewType(position) == ITEM_MOVIES) {
                (holder as PersonMovieViewHolder).bind(it)
            } else (holder as ShowAllViewHolder).bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 10) ITEM_SHOW_ALL
        else ITEM_MOVIES
    }

    companion object {
        private const val ITEM_MOVIES = 0
        private const val ITEM_SHOW_ALL = 1
    }
}

class PersonMovieViewHolder(
    val binding: ItemFilmBinding, val onPersonMovieItemClick: (PersonFilm) -> Unit
) : ViewHolder(binding.root) {
    fun bind(item: PersonFilm) {
        with(binding) {

            itemFilmPicture.isVisible = true
            itemFilmGenre.isVisible = false
            itemFilmTitle.isVisible = true
            itemFilmRating.isVisible = true

            if (item.posterUri != null) {
                Glide.with(itemFilmPicture.context).load(item.posterUri).centerCrop()
                    .into(itemFilmPicture)
            }

            itemFilmTitle.text = item.nameRu ?: item.nameEn ?: ""
            itemFilmRating.text = item.rating ?: ""

            binding.root.setOnClickListener {
                onPersonMovieItemClick(item)
            }
        }
    }
}


class PersonMovieDiffUtilCallBack : DiffUtil.ItemCallback<PersonFilm>() {
    override fun areItemsTheSame(oldItem: PersonFilm, newItem: PersonFilm): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: PersonFilm, newItem: PersonFilm): Boolean {
        return oldItem == newItem
    }
}

