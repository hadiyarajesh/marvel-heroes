package com.hadiyarajesh.marvel_heroes.di

import com.hadiyarajesh.marvel_heroes.repository.CharacterRepository
import com.hadiyarajesh.marvel_heroes.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindHomeRepository(homeRepositoryImpl: CharacterRepositoryImpl): CharacterRepository
}
