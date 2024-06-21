package com.example.cristian.listconsume.domain.useCases

import com.example.cristian.listconsume.domain.repositories.CategoriesRepository
import javax.inject.Inject

class GetCategoriesUseCaseImpl @Inject constructor(private val categoriesRepository: CategoriesRepository) :
    GetCategoriesUseCase {
    override suspend fun invoke(): Result<List<String>> = categoriesRepository.getCategories()
}