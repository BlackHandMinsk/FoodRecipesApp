package com.foodrecipesapp.usecases.local

import com.foodrecipesapp.data.Repository
import com.foodrecipesapp.data.database.entities.FavoritesEntity
import com.foodrecipesapp.data.database.entities.RecipesEntity
import javax.inject.Inject

class InsertLocalFavoritesRecipesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(favoritesEntity: FavoritesEntity)= repository.local.insertFavoriteRecipes(favoritesEntity)
}