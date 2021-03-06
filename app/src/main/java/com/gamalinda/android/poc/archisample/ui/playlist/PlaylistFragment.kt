package com.gamalinda.android.poc.archisample.ui.playlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamalinda.android.poc.archisample.MainApplication
import com.gamalinda.android.poc.archisample.databinding.FragmentPlaylistBinding

class PlaylistFragment : Fragment() {
    companion object {
        const val TX_TAG = "PlaylistFragmentTag"
        fun createInstance(): PlaylistFragment {
            return PlaylistFragment()
        }
    }

    private lateinit var binding: FragmentPlaylistBinding
    private lateinit var viewModel: PlaylistViewModel
    private lateinit var adapter: PlaylistAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)

        val factory = PlaylistViewModel.Factory(requireActivity().application as MainApplication)
        viewModel = ViewModelProvider(this, factory).get(PlaylistViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeAdapter()
        subscribeToPlaylistLiveData()
    }

    private fun initializeAdapter() {
        adapter = PlaylistAdapter()
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.playlistRecyclerView.adapter = adapter
        binding.playlistRecyclerView.layoutManager = layoutManager
        binding.playlistRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                layoutManager.orientation
            )
        )
    }

    private fun subscribeToPlaylistLiveData() {
        viewModel.getFetchState().observe(viewLifecycleOwner) {
            when (it) {
                PlaylistViewModel.FetchStatus.Initial -> {
                    Log.e("PLAYLIST", "Initial")
                }
                PlaylistViewModel.FetchStatus.Error -> {
                    Log.e("PLAYLIST", "Loading Error")
                    viewModel.getPlaylist()
                }
                PlaylistViewModel.FetchStatus.Loading -> {
                    Log.e("PLAYLIST", "Loading Playlist")
                }
                PlaylistViewModel.FetchStatus.Success -> {
                    Log.e("PLAYLIST", "Success Playlist")
                    viewModel.getPlaylist()
                }
            }
        }

        viewModel.getPlaylistState().observe(viewLifecycleOwner) {
            adapter.setNewItemList(it.videos)
            adapter.notifyDataSetChanged()
        }

        viewModel.fetchPlaylist()
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.getFetchState().value == PlaylistViewModel.FetchStatus.Initial) {
            viewModel.getPlaylist()
        }
    }
}
