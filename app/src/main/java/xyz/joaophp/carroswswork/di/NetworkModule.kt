package xyz.joaophp.carroswswork.di

import org.koin.dsl.module
import xyz.joaophp.carroswswork.service.remote.RetrofitClient

val networkModule = module {
    // Retrofit
    single {
        RetrofitClient.provideRetrofit()
    }
    single {
        RetrofitClient.provideApiService(get())
    }
}
