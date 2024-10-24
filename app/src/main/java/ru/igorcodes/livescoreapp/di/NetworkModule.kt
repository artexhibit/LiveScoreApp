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

    @Provides
    fun okHttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(RequestInterceptor()).build()
    }

    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    fun sportsDBApiService(retrofitBuilder: Retrofit.Builder, baseUrl: String): SportsDBApiService {
        return retrofitBuilder
            .baseUrl(baseUrl)
            .build()
            .create(SportsDBApiService::class.java)
    }
}