package com.gamalinda.android.poc.archisample.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamalinda.android.poc.shared.data.repository.PlaylistRepository
import com.gamalinda.android.poc.shared.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : ViewModel() {
    sealed class FetchStatus {
        object Initial : FetchStatus()
        object Loading : FetchStatus()
        data class Success(
            val video: Video
        ) : FetchStatus()

        object Error : FetchStatus()
    }

    private val videoState = MutableLiveData<FetchStatus>(FetchStatus.Initial)

    fun getVideoState(): LiveData<FetchStatus> = videoState

    fun getVideo(id: Int) {
        videoState.postValue(FetchStatus.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val playlist = playlistRepository.getPlaylist()
                videoState.postValue(FetchStatus.Success(playlist.videos[id]))
            } catch (e: Exception) {
                videoState.postValue(FetchStatus.Error)
            }
        }
    }
}
