package com.gamalinda.android.poc.archisample.ui.playlist

import androidx.lifecycle.*
import com.gamalinda.android.poc.archisample.MainApplication
import com.gamalinda.android.poc.archisample.data.repository.PlaylistRepository
import com.gamalinda.android.poc.archisample.data.repository.impl.PlaylistRepositoryImpl
import com.gamalinda.android.poc.archisample.model.Playlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaylistViewModel(
    private val playlistRepository: PlaylistRepository
) : ViewModel() {
    class Factory(private val mainApplication: MainApplication) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(PlaylistRepository::class.java).newInstance(
                PlaylistRepositoryImpl(mainApplication)
            )
        }
    }

    sealed class FetchStatus {
        object Initial : FetchStatus()
        object Loading : FetchStatus()
        object Success : FetchStatus()
        object Error : FetchStatus()
    }

    private val fetchStatus = MutableLiveData<FetchStatus>(FetchStatus.Initial)
    private val playlistState = MutableLiveData<Playlist>()

    fun getFetchState(): LiveData<FetchStatus> = fetchStatus
    fun getPlaylistState(): LiveData<Playlist> = playlistState

    fun fetchPlaylist() {
        fetchStatus.postValue(FetchStatus.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                playlistRepository.fetchPlaylist()
                fetchStatus.postValue(FetchStatus.Success)
            } catch (e: Exception) {
                fetchStatus.postValue(FetchStatus.Error)
            }
        }
    }

    fun getPlaylist() {
        viewModelScope.launch(Dispatchers.IO) {
            val playlist = playlistRepository.getPlaylist()
            playlistState.postValue(playlist)
        }
    }
}
