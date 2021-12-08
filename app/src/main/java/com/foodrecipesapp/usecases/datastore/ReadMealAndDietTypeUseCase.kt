package com.foodrecipesapp.usecases.datastore

import com.foodrecipesapp.data.DataStoreRepository
import javax.inject.Inject

class ReadMealAndDietTypeUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    operator fun invoke() = dataStoreRepository.readMealAndDietType
}