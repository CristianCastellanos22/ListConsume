package com.example.cristian.listconsume.domain.useCases

interface GetCategoriesUseCase {
    suspend fun invoke(): Result<List<String>>
}