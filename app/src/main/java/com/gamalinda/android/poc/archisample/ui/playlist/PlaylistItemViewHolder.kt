package com.gamalinda.android.poc.archisample.ui.playlist

import androidx.recyclerview.widget.RecyclerView
import com.gamalinda.android.poc.archisample.databinding.ListItemPlaylistBinding
import com.gamalinda.android.poc.archisample.model.Video
import com.squareup.picasso.Picasso

class PlaylistItemViewHolder(private val viewBinding: ListItemPlaylistBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(item: Video) {
        viewBinding.titleTextView.text = item.title
        viewBinding.authorTextView.text = item.author
        viewBinding.descriptionTextView.text = item.description
        if (item.thumbnailUrl.isNotBlank()) {
            Picasso.get().load(item.thumbnailUrl).into(viewBinding.thumbnailImageView)
        }
    }
}
