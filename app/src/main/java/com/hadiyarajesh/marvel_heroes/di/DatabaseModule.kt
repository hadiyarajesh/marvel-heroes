package com.hadiyarajesh.marvel_heroes.di

import android.content.Context
import androidx.room.Room
import com.hadiyarajesh.marvel_heroes.R
import com.hadiyarajesh.marvel_heroes.data.AppDatabase
import com.hadiyarajesh.marvel_heroes.data.dao.ComicCharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java, context.getString(
                R.string.app_name
            )
        ).build()
    }

    @Singleton
    @Provides
    fun provideComicCharacterDao(appDatabase: AppDatabase): ComicCharacterDao =
        appDatabase.comicCharacterDao()
}