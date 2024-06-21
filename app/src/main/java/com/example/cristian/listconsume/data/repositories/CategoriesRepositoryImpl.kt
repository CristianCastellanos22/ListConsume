package com.example.cristian.listconsume.data.repositories

import com.example.cristian.listconsume.data.ApiService
import com.example.cristian.listconsume.data.utils.resultOf
import com.example.cristian.listconsume.domain.repositories.CategoriesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
) : CategoriesRepository {
    override suspend fun getCategories(): Result<List<String>> = resultOf {
        val categories = withContext(dispatcher) {
            apiService.getCategories()
        }
        val body = categories.body()
        if (categories.isSuccessful && body != null) {
            body
        } else {
            val errorMessage = categories.errorBody().toString()
            error(errorMessage)
        }
    }
}