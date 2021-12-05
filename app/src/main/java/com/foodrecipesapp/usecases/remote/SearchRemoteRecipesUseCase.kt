package com.foodrecipesapp.usecases.remote

import com.foodrecipesapp.data.Repository
import javax.inject.Inject

class SearchRemoteRecipesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(searchQuery:Map<String,String>) = repository.remote.searchRecipes(searchQuery)
}