package xyz.joaophp.carroswswork.service.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.joaophp.carroswswork.BuildConfig

object RetrofitClient {

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
