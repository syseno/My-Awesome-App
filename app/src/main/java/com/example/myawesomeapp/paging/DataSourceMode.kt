package com.example.myawesomeapp.paging

sealed class DataSourceMode {
    class LIVEDATA : DataSourceMode()
    class RX : DataSourceMode()
}