package com.foodrecipesapp.usecases.local

import androidx.lifecycle.asLiveData
import com.foodrecipesapp.data.Repository
import javax.inject.Inject

class ReadLocalFavoriteRecipesUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke () = repository.local.readFavoriteRecipes().asLiveData()
}