package com.foodrecipesapp.usecases.datastore

import com.foodrecipesapp.data.DataStoreRepository
import javax.inject.Inject

class SaveBackOnlineUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
  suspend operator fun invoke(bacOnline:Boolean) = dataStoreRepository.saveBackOnline(bacOnline)
}