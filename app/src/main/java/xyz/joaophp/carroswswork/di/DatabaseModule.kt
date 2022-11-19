package xyz.joaophp.carroswswork.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import xyz.joaophp.carroswswork.service.local.CarrosDatabase

val databaseModule = module {
    single {
        CarrosDatabase.build(androidApplication())
    }

    single {
        get<CarrosDatabase>().carroDao()
    }

    single {
        get<CarrosDatabase>().leadDao()
    }

    single {
        get<CarrosDatabase>().perfilDao()
    }
}