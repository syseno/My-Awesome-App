package com.example.myawesomeapp.paging

class PagingItem<DataType>(
val data: DataType? = null,
val itemType: ItemType,
val itemData: ItemData? = null
) {
    enum class ItemType(val itemCode: Int) {
        LIST(11),
        GRID(22)
    }

    data class ItemData(
        val title: String,
        val message: String
    )
}