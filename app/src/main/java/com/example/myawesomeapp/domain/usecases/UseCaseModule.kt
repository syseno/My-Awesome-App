package com.example.myawesomeapp.domain.usecases

import org.koin.dsl.module

val useCaseModule = module {
    factory { DisplayingPhotoUseCase(get()) }
}