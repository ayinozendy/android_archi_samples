package com.gamalinda.android.poc.archisample.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.gamalinda.android.poc.archisample.databinding.ListItemPlaylistBinding
import com.gamalinda.android.poc.archisample.model.Video

class PlaylistAdapter : RecyclerView.Adapter<PlaylistItemViewHolder>() {
    private val itemList = mutableListOf<Video>()
    private val clickState = MutableLiveData<Int>()

    fun setNewItemList(newItems: List<Video>) {
        itemList.clear()
        itemList.addAll(newItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPlaylistBinding.inflate(inflater, parent, false)
        return PlaylistItemViewHolder(binding).apply {
            binding.root.setOnClickListener {
                clickState.postValue(item.id)
            }
        }
    }

    override fun onBindViewHolder(holder: PlaylistItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.count()

    fun getClickState(): LiveData<Int> = clickState
}
