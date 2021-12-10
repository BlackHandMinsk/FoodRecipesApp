package com.foodrecipesapp.usecases.datastore

import androidx.lifecycle.asLiveData
import com.foodrecipesapp.data.DataStoreRepository
import javax.inject.Inject

class ReadBackOnlineUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
     operator fun invoke() = dataStoreRepository.readBackOnline.asLiveData()
}