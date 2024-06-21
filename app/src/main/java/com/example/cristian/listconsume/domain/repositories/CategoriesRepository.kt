package com.example.cristian.listconsume.domain.repositories

interface CategoriesRepository {
    suspend fun getCategories(): Result<List<String>>
}