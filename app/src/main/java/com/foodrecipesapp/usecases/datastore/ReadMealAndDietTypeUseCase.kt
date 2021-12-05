package com.foodrecipesapp.usecases.datastore

import com.foodrecipesapp.data.DataStoreRepository
import com.foodrecipesapp.data.DataStoreRepository_Factory
import javax.inject.Inject

class ReadMealAndDietTypeUseCase @Inject constructor(private val dataStoreRepository:DataStoreRepository) {
    operator fun invoke() = dataStoreRepository.readMealAndDietType
}