package com.foodrecipesapp.data.network

import com.foodrecipesapp.models.FoodRecipe
import com.foodrecipesapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipesApi {


    @GET("/recipes/complexSearch")
   suspend fun getRecipes(@QueryMap queries:Map<String,String>):Response<FoodRecipe>


    @GET("/recipes/complexSearch")
   suspend fun searchRecipes(@QueryMap searchQuery:Map<String,String>):Response<FoodRecipe>
}