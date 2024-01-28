package com.hadiyarajesh.marvel_heroes.di

import com.hadiyarajesh.marvel_heroes.BuildConfig
import com.hadiyarajesh.marvel_heroes.data.network.MarvelApi
import com.hadiyarajesh.marvel_heroes.data.network.RequestInterceptor
import com.hadiyarajesh.marvel_heroes.utility.Constants
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(RequestInterceptor())
            .also { okHttpClient ->
                /**
                 * Only add [HttpLoggingInterceptor] on debug build
                 */
                if (BuildConfig.DEBUG) {
                    okHttpClient.addInterceptor(getLoggingInterceptor())
                }
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.MARVEL_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi)) // Register Moshi as a JSON converter for serialization and deserialization of objects.
            .build()
    }

    @Provides
    @Singleton
    fun provideMarvelApi(retrofit: Retrofit): MarvelApi = retrofit.create(MarvelApi::class.java)
}
