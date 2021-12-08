package com.foodrecipesapp.viewmodels

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.foodrecipesapp.data.MealAndDietType
import com.foodrecipesapp.data.database.entities.FavoritesEntity
import com.foodrecipesapp.data.database.entities.RecipesEntity
import com.foodrecipesapp.helpers.TestCoroutinesRule
import com.foodrecipesapp.helpers.TestUtil
import com.foodrecipesapp.models.Result
import com.foodrecipesapp.usecases.datastore.ReadBackOnlineUseCase
import com.foodrecipesapp.usecases.datastore.ReadMealAndDietTypeUseCase
import com.foodrecipesapp.usecases.datastore.SaveBackOnlineUseCase
import com.foodrecipesapp.usecases.datastore.SaveMealAndDietTypeUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RecipesViewModelTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = TestCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var testViewModel: RecipesViewModel

    private lateinit var mockMealAndDietType:MealAndDietType

    private  var application: Application = Application()


    //Datastore
    @MockK
    private lateinit var testReadBackOnlineUseCase: ReadBackOnlineUseCase
    @MockK
    private lateinit var testReadMealAndDietTypeUseCase: ReadMealAndDietTypeUseCase
    @MockK
    private lateinit var testSaveBackOnlineUseCase: SaveBackOnlineUseCase
    @MockK
    private lateinit var testSaveMealAndDietTypeUseCase: SaveMealAndDietTypeUseCase


    fun setUp() {
        MockKAnnotations.init(this)
        val id = 1
        val testId = 58782L
        mockMealAndDietType = TestUtil.createFakeMealAndDietType()

        testViewModel = RecipesViewModel(
            application,
            testReadMealAndDietTypeUseCase,
            testReadBackOnlineUseCase,
            testSaveMealAndDietTypeUseCase,
            testSaveBackOnlineUseCase,
        )


    }

    @Test
    fun getNetworkStatus() {
    }

    @Test
    fun setNetworkStatus() {
    }

    @Test
    fun getBackOnline() {
    }

    @Test
    fun setBackOnline() {
    }

    @Test
    fun getReadMealAndDietType() {
    }

    @Test
    fun getReadBackOnline() {
    }

    @Test
    fun saveMealAndDietType() {
    }

    @Test
    fun saveMealAndDietTypeTemp() {
    }

    @Test
    fun saveBackOnline() {
    }

    @Test
    fun applyQueries() {
    }

    @Test
    fun applySearchQuery() {
    }

//    @Test
//    fun showNetworkStatus() {
//        val resultResponse = testViewModel.networkStatus
//        assertEquals(resultResponse, CoreMatchers.notNullValue())
//        assertEquals(resultResponse,Boolean)
//    }
}