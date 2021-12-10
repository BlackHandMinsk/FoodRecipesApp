package com.foodrecipesapp.usecases.local

import com.foodrecipesapp.data.Repository
import com.foodrecipesapp.data.database.entities.RecipesEntity
import javax.inject.Inject

class InsertLocalRecipesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(recipesEntity: RecipesEntity)= repository.local.insertRecipes(recipesEntity)
}