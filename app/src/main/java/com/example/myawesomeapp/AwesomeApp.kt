package com.example.myawesomeapp

import android.app.Application
import com.example.myawesomeapp.data.dataSourceModule
import com.example.myawesomeapp.database.databaseModule
import com.example.myawesomeapp.domain.usecases.useCaseModule
import com.example.myawesomeapp.networks.networkModule
import com.example.myawesomeapp.repositories.curatedRepositoryModule
import com.example.myawesomeapp.viewmodels.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AwesomeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AwesomeApp)
            modules(
                listOf(
                    databaseModule,
                    dataSourceModule,
                    networkModule,
                    curatedRepositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}