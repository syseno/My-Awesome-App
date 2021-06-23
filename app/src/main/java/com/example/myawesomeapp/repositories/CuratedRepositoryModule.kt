package com.example.myawesomeapp.repositories

import org.koin.core.qualifier.named
import org.koin.dsl.module

val curatedRepositoryModule = module {
    single<CuratedRepository> { CuratedRepositoryImpl(get(named("local")), get(named("remote"))) }
}