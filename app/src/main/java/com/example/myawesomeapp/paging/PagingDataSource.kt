package com.example.myawesomeapp.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

open class PagingDataSource<ItemType>(
    private val dataSourceMode: DataSourceMode = DataSourceMode.RX()
) {

    private var _itemsChannelLiveData: MutableLiveData<List<PagingItem<ItemType>>> = MutableLiveData()
    val itemsChannelLiveData: LiveData<List<PagingItem<ItemType>>>
        get() = _itemsChannelLiveData

    private var _itemsChannelRx: BehaviorSubject<List<PagingItem<ItemType>>> = BehaviorSubject.create()
    val itemsChannelRx: Observable<List<PagingItem<ItemType>>>
        get() = _itemsChannelRx.hide()

    private var _items: MutableList<PagingItem<ItemType>> = mutableListOf()
    val items: List<PagingItem<ItemType>>
        get() = _items

    val itemCount: Int
        get() = _items.size

    private var lastFooterPosition: Int? = null
    private var hasFooter = false

    fun addItems(items: List<PagingItem<ItemType>>) {
        _items.addAll(items)
        pushUpdatedItems()
    }

    fun addItem(newItem: PagingItem<ItemType>) {
        _items.add(newItem)
        pushUpdatedItems()
    }

    fun removeItemAtPosition(position: Int) {
        _items.removeAt(position)
        pushUpdatedItems()
    }

    fun insertItemAtPosition(position: Int, item: PagingItem<ItemType>) {
        _items.add(position, item)
        pushUpdatedItems()
    }

    fun removeFooter() {
        if (hasFooter) {
            lastFooterPosition?.let {
                removeItemAtPosition(it)
                hasFooter = false
            }
        }
    }

    fun clearItems() {
        _items.clear()
        pushUpdatedItems()
    }

    private fun pushUpdatedItems() {
        when (dataSourceMode) {
            is DataSourceMode.LIVEDATA -> {
                _itemsChannelLiveData.value = _items
            }
            is DataSourceMode.RX -> {
                _itemsChannelRx.onNext(_items)
            }
        }
    }
}