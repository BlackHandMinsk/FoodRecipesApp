package com.foodrecipesapp.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.foodrecipesapp.data.Repository
import com.foodrecipesapp.data.database.entities.FavoritesEntity
import com.foodrecipesapp.data.database.entities.RecipesEntity
import com.foodrecipesapp.models.FoodRecipe
import com.foodrecipesapp.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel @ViewModelInject constructor(
    private val repository: Repository,
    application: Application):AndroidViewModel(application) {

    //Room

    val readRecipes:LiveData<List<RecipesEntity>> = repository.local.readRecipes().asLiveData()
    val readFavoriteRecipes:LiveData<List<FavoritesEntity>> = repository.local.readFavoriteRecipes().asLiveData()

    private fun insertRecipes(recipesEntity: RecipesEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.local.insertRecipes(recipesEntity)
    }

     fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.local.insertFavoriteRecipes(favoritesEntity)
    }


     fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.local.deleteFavoriteRecipe(favoritesEntity)
    }


     fun deleteAllFavoriteRecipes() = viewModelScope.launch(Dispatchers.IO){
        repository.local.deleteAllFavoriteRecipes()
    }


    ///Retrofit
    val recipesResponse:MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()
    var searchedRecipesResponse:MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(queries:Map<String,String>) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    fun searchRecipes(searchQuery:Map<String,String>) = viewModelScope.launch {
        searchRecipesSafeCall(searchQuery)
    }



    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        recipesResponse.value = NetworkResult.Loading()
    if (hasInternetConnection()){
        try {
            val response = repository.remote.getRecipes(queries)
            recipesResponse.value = handleFoodRecipesResponse(response)

            val fooRecipe = recipesResponse.value!!.data
            if (fooRecipe!=null){
                offlineCacheRecipe(fooRecipe)
            }
        }catch (e:Exception){
            recipesResponse.value = NetworkResult.Error("Recipes not found.")
        }
        }else{
            recipesResponse.value = NetworkResult.Error("No Internet Connection")
    }
    }

    private suspend fun searchRecipesSafeCall(searchQuery: Map<String, String>) {
        searchedRecipesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()){
            try {
                val response = repository.remote.searchRecipes(searchQuery)
                searchedRecipesResponse.value = handleFoodRecipesResponse(response)
            }catch (e:Exception){
                searchedRecipesResponse.value = NetworkResult.Error("Recipes not found.")
            }
        }else{
            searchedRecipesResponse.value = NetworkResult.Error("No Internet Connection")
        }
    }

    private fun offlineCacheRecipe(fooRecipe: FoodRecipe) {
    val recipesEntity = RecipesEntity(fooRecipe)
        insertRecipes(recipesEntity)
    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return  NetworkResult.Error("API Key Limited.")
            }
            response.body()!!.results.isNullOrEmpty()->{
                return NetworkResult.Error("Recipes not found.")
            }
            response.isSuccessful ->{
                val foodRecipes = response.body()
                return NetworkResult.Success(foodRecipes!!)
            }
            else->{return NetworkResult.Error(response.message())}
        }
    }

    private fun hasInternetConnection():Boolean{

            val connectivityManager = getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetwork?:return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)?:return false
            return when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
                else->false
            }
        }
}