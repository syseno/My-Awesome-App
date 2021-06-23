package com.example.myawesomeapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myawesomeapp.domain.entities.PhotoEntity
import com.example.myawesomeapp.domain.usecases.DisplayingPhotoUseCase
import com.example.myawesomeapp.paging.PagingItem
import com.example.myawesomeapp.paging.PagingUpdater
import com.example.myawesomeapp.paging.PhotoPagingUpdater
import com.example.myawesomeapp.paging.UpdaterType
import com.example.myawesomeapp.tools.ProgressState

class MainViewModel(private val useCase: DisplayingPhotoUseCase): BaseViewModel() {
    val photos: LiveData<List<PagingItem<PhotoEntity>>>
        get() = photoPagingUpdater.pagingDataSource.itemsChannelLiveData

    var photoPagingUpdater: PagingUpdater<PhotoEntity> =
        PhotoPagingUpdater(
            photoDisplayingUseCase = useCase,
            type = UpdaterType.CURATED,
            emptyResultListener = { _progressState.value = ProgressState.EMPTY() },
            errorListener = { error -> _progressState.value = ProgressState.ERROR(error) },
            viewModelScope = viewModelScope
        )

    fun repeatFetch() {
        _progressState.value = ProgressState.DONE()
        photoPagingUpdater.fetchPage(false)
    }
}