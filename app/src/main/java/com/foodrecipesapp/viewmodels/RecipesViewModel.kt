package com.foodrecipesapp.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.foodrecipesapp.data.DataStoreRepository
import com.foodrecipesapp.data.MealAndDietType
import com.foodrecipesapp.usecases.datastore.ReadBackOnlineUseCase
import com.foodrecipesapp.usecases.datastore.ReadMealAndDietTypeUseCase
import com.foodrecipesapp.usecases.datastore.SaveBackOnlineUseCase
import com.foodrecipesapp.usecases.datastore.SaveMealAndDietTypeUseCase
import com.foodrecipesapp.util.Constants.Companion.API_KEY
import com.foodrecipesapp.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.foodrecipesapp.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.foodrecipesapp.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import com.foodrecipesapp.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.foodrecipesapp.util.Constants.Companion.QUERY_API_KEY
import com.foodrecipesapp.util.Constants.Companion.QUERY_DIET
import com.foodrecipesapp.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.foodrecipesapp.util.Constants.Companion.QUERY_NUMBER
import com.foodrecipesapp.util.Constants.Companion.QUERY_SEARCH
import com.foodrecipesapp.util.Constants.Companion.QUERY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application,
    private val readMealAndDietTypeUseCase: ReadMealAndDietTypeUseCase,
    private val readBackOnlineUseCase: ReadBackOnlineUseCase,
    private val saveMealAndDietTypeUseCase: SaveMealAndDietTypeUseCase,
    private val saveBackOnlineUseCase:SaveBackOnlineUseCase,
    private val dataStoreRepository: DataStoreRepository ) : AndroidViewModel(application) {


    private lateinit var mealAndDiet: MealAndDietType


    var networkStatus = false
    var backOnline = false

    val readMealAndDietType = readMealAndDietTypeUseCase.invoke()
    //= dataStoreRepository.readMealAndDietType
    val readBackOnline = readBackOnlineUseCase.invoke()
    //= dataStoreRepository.readBackOnline.asLiveData()


    fun saveMealAndDietType() =
        viewModelScope.launch(Dispatchers.IO) {
            if (this@RecipesViewModel::mealAndDiet.isInitialized) {
                saveMealAndDietTypeUseCase.invoke(
                    mealAndDiet.selectedMealType,
                    mealAndDiet.selectedMealTypeId,
                    mealAndDiet.selectedDietType,
                    mealAndDiet.selectedDietTypeId
                )
//                dataStoreRepository.saveMealAndDietType(
//                    mealAndDiet.selectedMealType,
//                    mealAndDiet.selectedMealTypeId,
//                    mealAndDiet.selectedDietType,
//                    mealAndDiet.selectedDietTypeId
//                )
            }
        }

    fun saveMealAndDietTypeTemp(mealType:String,mealTypeId:Int,dietType:String,dietTypeId:Int) {
        mealAndDiet = MealAndDietType(mealType,mealTypeId,dietType,dietTypeId)
    }

    fun saveBackOnline(backOnline:Boolean) = viewModelScope.launch(Dispatchers.IO) {
        saveBackOnlineUseCase.invoke(backOnline)
       // dataStoreRepository.saveBackOnline(backOnline)
    }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()


        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealAndDiet.selectedMealType
        queries[QUERY_DIET] = mealAndDiet.selectedDietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }


    fun applySearchQuery(searchQuery:String):HashMap<String,String>{
        val queries:HashMap<String,String> = HashMap()
        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }


    fun showNetworkStatus(){
        if (!networkStatus){
            Toast.makeText(getApplication(),"No internet Connection.",Toast.LENGTH_SHORT).show()
            saveBackOnline(true)
        }else if (networkStatus){
            if (backOnline){
                Toast.makeText(getApplication(),"We are back online.",Toast.LENGTH_SHORT).show()
                saveBackOnline(false)
            }
        }
    }
}