package com.example.myawesomeapp.data

import com.example.myawesomeapp.data.local.LocalDataSource
import com.example.myawesomeapp.data.local.LocalDataSourceImpl
import com.example.myawesomeapp.data.remote.RemoteDataSource
import com.example.myawesomeapp.data.remote.RemoteDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    single<LocalDataSource>(named("local")) { LocalDataSourceImpl(get()) }
    single<RemoteDataSource>(named("remote")) { RemoteDataSourceImpl(get()) }
}