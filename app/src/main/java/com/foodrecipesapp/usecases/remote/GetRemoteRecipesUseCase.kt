package com.foodrecipesapp.usecases.remote

import com.foodrecipesapp.data.Repository
import javax.inject.Inject

class GetRemoteRecipesUseCase @Inject constructor(private val repository: Repository) {
  suspend  operator fun invoke(queries:Map<String,String>) = repository.remote.getRecipes(queries)
}