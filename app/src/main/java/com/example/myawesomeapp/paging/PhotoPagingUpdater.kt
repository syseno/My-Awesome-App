package com.example.myawesomeapp.paging

import com.example.myawesomeapp.domain.entities.PhotoEntity
import com.example.myawesomeapp.domain.usecases.DisplayingPhotoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

class PhotoPagingUpdater(
    private val type: UpdaterType,
    private val photoDisplayingUseCase: DisplayingPhotoUseCase? = null,
    private val loadingListener: (() -> Unit)? = null,
    private val doneListener: (() -> Unit)? = null,
    private val errorListener: ((error: String) -> Unit)? = null,
    private val emptyResultListener: (() -> Unit)? = null,
    private val viewModelScope: CoroutineScope
) : PagingUpdater<PhotoEntity>(
    pagingDataSource = PagingDataSource(DataSourceMode.LIVEDATA()),
    pagingMode = PagingMode.BY_PAGE(),
    pageSize = 15,
    currentPage = 1
) {

    override fun fetchPage(usePageUpdate: Boolean) {
        when (type) {
            UpdaterType.CURATED -> {
                viewModelScope.launch {
                    delay(250)
                    if (currentPage == initialPage) loadingListener?.invoke()
                    try {
                        proceedPhotosData(
                            remotePhotosToProceed = photoDisplayingUseCase?.getCuratedPhotos(currentPage, pageSize),
                            usePageUpdate = usePageUpdate
                        )
                    } catch (ex: IOException) {
                        errorListener?.invoke(ex.message ?: "")
                    }
                }
            }
        }
    }

    private fun proceedPhotosData(
        remotePhotosToProceed: List<PhotoEntity>? = null,
        localPhotosToProceed: List<PhotoEntity>? = null,
        usePageUpdate: Boolean = true
    ) {
        remotePhotosToProceed?.let {
        pushPhotosWithPagingIncrement(it, usePageUpdate) }

        localPhotosToProceed?.let {
            pushPhotosWithPagingIncrement(it, usePageUpdate)
        }
    }

    private fun pushPhotosWithPagingIncrement(photos: List<PhotoEntity>, usePageUpdate: Boolean) {
        if (currentPage == initialPage) doneListener?.invoke()
        if (photos.isNullOrEmpty()) emptyResultListener?.invoke()
        pagingDataSource.removeFooter()
        pushToDataSource(mapToItems(photos))
        if (usePageUpdate) updateCurrentPage(photos.size)
    }
}