package xyz.joaophp.carroswswork.di

import org.koin.dsl.module
import xyz.joaophp.carroswswork.service.repositories.CarroRepository

val repositoryModule = module {
    single {
        CarroRepository(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
}