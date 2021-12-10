package com.foodrecipesapp.usecases.local

import com.foodrecipesapp.data.Repository
import javax.inject.Inject

class DeleteLocalAllFavoritesRecipesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.local.deleteAllFavoriteRecipes()
}