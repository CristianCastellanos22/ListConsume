package com.example.cristian.listconsume.di

import com.example.cristian.listconsume.domain.repositories.CategoriesRepository
import com.example.cristian.listconsume.domain.useCases.GetCategoriesUseCase
import com.example.cristian.listconsume.domain.useCases.GetCategoriesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCase {
    @Provides
    @ViewModelScoped
    fun provideGetCategoriesUseCase(categoriesRepository: CategoriesRepository): GetCategoriesUseCase {
        return GetCategoriesUseCaseImpl(categoriesRepository)
    }
}