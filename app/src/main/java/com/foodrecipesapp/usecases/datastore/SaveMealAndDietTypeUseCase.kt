package com.foodrecipesapp.usecases.datastore

import com.foodrecipesapp.data.DataStoreRepository
import javax.inject.Inject

class SaveMealAndDietTypeUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository){
    suspend operator fun invoke(mealType:String,mealTypeId:Int,dietType:String,dietTypeId:Int) = dataStoreRepository.saveMealAndDietType(mealType,mealTypeId,dietType,dietTypeId)
}