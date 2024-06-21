package com.example.cristian.listconsume.di

import com.example.cristian.listconsume.data.ApiService
import com.example.cristian.listconsume.data.repositories.CategoriesRepositoryImpl
import com.example.cristian.listconsume.domain.repositories.CategoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object Repository {
    @Provides
    fun provideDispatcher() = Dispatchers.IO

    @Provides
    fun provideGetCategories(
        apiService: ApiService
    ): CategoriesRepository {
        return CategoriesRepositoryImpl(apiService, provideDispatcher())
    }
}