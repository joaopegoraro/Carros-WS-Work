package xyz.joaophp.carroswswork.di

import org.koin.dsl.module
import xyz.joaophp.carroswswork.service.repositories.CarroRepository
import xyz.joaophp.carroswswork.service.repositories.PerfilRepository

val repositoryModule = module {
    single {
        CarroRepository(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }

    single {
        PerfilRepository(
            localDataSource = get()
        )
    }
}