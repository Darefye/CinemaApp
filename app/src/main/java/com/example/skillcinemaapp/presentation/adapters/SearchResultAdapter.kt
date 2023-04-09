package com.example.skillcinemaapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.skillcinemaapp.data.remote.custom_selection_dto.ItemCustom
import com.example.skillcinemaapp.databinding.ItemFilmographyDetailBinding


class SearchResultAdapter(
    val onSearchMovieItemClick: (ItemCustom) -> Unit
) : PagingDataAdapter<ItemCustom, SearchViewHolder>(DiffUtilCallBackCustom()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            binding = ItemFilmographyDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onSearchMovieItemClick = onSearchMovieItemClick
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }
}

class SearchViewHolder(
    val binding: ItemFilmographyDetailBinding,
    val onSearchMovieItemClick: (ItemCustom) -> Unit
) : ViewHolder(binding.root) {
    fun bind(item: ItemCustom) {
        with(binding) {

            filmPicture.isVisible = true
            filmInfo.isVisible = true
            filmTitle.isVisible = true
            filmRating.isVisible = true

            filmRating.text =
                when{
                    item.ratingKinopoisk!=null -> item.ratingKinopoisk.toString()
                    item.ratingImdb!=null -> item.ratingImdb.toString()
                    else -> ""
                }

            filmInfo.text = buildString {
                if(item.year!=null){
                    append(item.year)
                    append(", ")
                }
                append(item.genres.firstOrNull()?.genre)
            }

            Glide
                .with(filmPicture.context)
                .load(item.posterUrl)
                .centerCrop()
                .into(filmPicture)


            filmTitle.text = when{
                item.nameRu!=null -> item.nameRu.toString()
                item.nameEn!=null -> item.nameEn.toString()
                item.nameOriginal!=null -> item.nameOriginal.toString()
                else -> ""
            }

            watchedStatus.isVisible = item.watchedStatus == true

            binding.root.setOnClickListener {
                onSearchMovieItemClick(item)
            }
        }
    }
}