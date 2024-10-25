package ru.igorcodes.livescoreapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.igorcodes.livescoreapp.data.remote.SportsDBApiService
import ru.igorcodes.livescoreapp.util.BASE_URL
import ru.igorcodes.livescoreapp.util.RequestInterceptor

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun buildRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    fun okHttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(RequestInterceptor()).build()
    }

    @Provides
    fun sportsDBApiService(okHttpClient: OkHttpClient): SportsDBApiService {
        return buildRetrofit(okHttpClient, BASE_URL).create(SportsDBApiService::class.java)
    }
}