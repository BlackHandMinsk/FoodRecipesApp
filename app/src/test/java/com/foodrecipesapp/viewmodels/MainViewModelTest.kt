package com.foodrecipesapp.viewmodels

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.foodrecipesapp.data.database.entities.FavoritesEntity
import com.foodrecipesapp.data.database.entities.RecipesEntity
import com.foodrecipesapp.models.FoodRecipe
import com.foodrecipesapp.models.Result
import com.foodrecipesapp.usecases.datastore.ReadBackOnlineUseCase
import com.foodrecipesapp.usecases.datastore.ReadMealAndDietTypeUseCase
import com.foodrecipesapp.usecases.datastore.SaveBackOnlineUseCase
import com.foodrecipesapp.usecases.datastore.SaveMealAndDietTypeUseCase
import com.foodrecipesapp.usecases.local.*
import com.foodrecipesapp.usecases.remote.GetRemoteRecipesUseCase
import com.foodrecipesapp.usecases.remote.SearchRemoteRecipesUseCase
import com.foodrecipesapp.util.NetworkResult
import com.foodrecipesapp.helpers.TestCoroutinesRule
import com.foodrecipesapp.helpers.TestUtil
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

//@ExperimentalCoroutinesApi
//@RunWith(JUnit4::class)
//class MainViewModelTest {
//
//    @ExperimentalCoroutinesApi
//    @get:Rule
//    var coroutinesRule = TestCoroutinesRule()
//
//    @get:Rule
//    var instantExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var testViewModel: MainViewModel
//    private lateinit var mockResult: Result
//
//    private lateinit var mockFavoritesEntity: FavoritesEntity
//    private lateinit var mockFavoritesEntity2: FavoritesEntity
//    private lateinit var mockRecipesEntity: RecipesEntity
//
//    private  var application: Application = Application()
//
//
//    //Remote
//    @MockK
//    private lateinit var testGetRemoteRecipesUseCase: GetRemoteRecipesUseCase
//    @MockK
//    private lateinit var testSearchRemoteRecipesUseCase: SearchRemoteRecipesUseCase
//    //Local
//    @MockK
//    private lateinit var testDeleteLocalAllFavoritesRecipesUseCase: DeleteLocalAllFavoritesRecipesUseCase
//    @MockK
//    private lateinit var testDeleteLocalFavoriteRecipesUseCase: DeleteLocalFavoriteRecipesUseCase
//    @MockK
//    private lateinit var testInsertLocalFavoritesRecipesUseCase:InsertLocalFavoritesRecipesUseCase
//    @MockK
//    private lateinit var testInsertLocalRecipesUseCase: InsertLocalRecipesUseCase
//    @MockK
//    private lateinit var testReadLocalFavoritesRecipesUseCase: ReadLocalFavoriteRecipesUseCase
//    @MockK
//    private lateinit var testReadLocalRecipesUseCase: ReadLocalRecipesUseCase
//    //Datastore
//    @MockK
//    private lateinit var testReadBackOnlineUseCase: ReadBackOnlineUseCase
//    @MockK
//    private lateinit var testReadMealAndDietTypeUseCase: ReadMealAndDietTypeUseCase
//    @MockK
//    private lateinit var testSaveBackOnlineUseCase: SaveBackOnlineUseCase
//    @MockK
//    private lateinit var testSaveMealAndDietTypeUseCase: SaveMealAndDietTypeUseCase
//
//
//    @Before
//    fun setUp() {
//        MockKAnnotations.init(this)
//        val id = 1
//        val testId = 58782L
//
//        mockResult = TestUtil.createFakeResult(testId)
//        mockFavoritesEntity = TestUtil.createFakeFavoritesEntity(1,testId)
//        mockRecipesEntity = TestUtil.createFakeRecipesEntity()
//
//        testViewModel = MainViewModel(
//            testDeleteLocalFavoriteRecipesUseCase,
//            testDeleteLocalAllFavoritesRecipesUseCase,
//            testInsertLocalFavoritesRecipesUseCase,
//            testInsertLocalRecipesUseCase,
//            testReadLocalRecipesUseCase,
//            testReadLocalFavoritesRecipesUseCase,
//            testGetRemoteRecipesUseCase,
//            testSearchRemoteRecipesUseCase,
//            application
//            )
//
//    }
//
//    @After
//    fun tearDown() {
//        unmockkAll()
//    }
//
////    @Test
////    fun testMainViewModel_callFunGetReadRecipes() = runBlockingTest{
////        val resultResponse = testReadLocalRecipesUseCase.invoke()
////        assertEquals(resultResponse, notNullValue())
////    }
////
////    @Test
////    fun testMainViewModel_callFunGetReadFavoriteRecipes() {
////        val resultResponse = testReadLocalFavoritesRecipesUseCase.invoke()
////        assertEquals(resultResponse, notNullValue())
////    }
////
////    @Test
////    fun testMainViewModel_callFunInsertFavoriteRecipe() = runBlocking {
////      coEvery { testInsertLocalFavoritesRecipesUseCase.invoke(any())} returns Unit
////        testViewModel.insertFavoriteRecipe(mockFavoritesEntity)
////        testViewModel.deleteFavoriteRecipe(mockFavoritesEntity)
////        ///// хуй пойми
////        ///чтото дописал
////        coVerify { testInsertLocalFavoritesRecipesUseCase.invoke(any())}
////
////    }
////
////    @Test
////    fun testMainViewModel_callFunDeleteFavoriteRecipe() = runBlockingTest {
////        coEvery { testDeleteLocalFavoriteRecipesUseCase.invoke(any()) } returns Unit
////        testViewModel.insertFavoriteRecipe(mockFavoritesEntity)
////        testViewModel.deleteFavoriteRecipe(mockFavoritesEntity)
////        coVerify { testDeleteLocalFavoriteRecipesUseCase.invoke(any()) }
////    }
////
////    @Test
////    fun testMainViewModel_callFunDeleteAllFavoriteRecipes()  = runBlockingTest {
////        coEvery { testDeleteLocalFavoriteRecipesUseCase.invoke(any()) } returns Unit
////        testViewModel.insertFavoriteRecipe(mockFavoritesEntity)
////        testViewModel.insertFavoriteRecipe(mockFavoritesEntity2)
////        testViewModel.deleteAllFavoriteRecipes()
////        coVerify { testDeleteLocalFavoriteRecipesUseCase.invoke(any()) }
////    }
////
////    @Test
////    fun testMainViewModel_call_getRecipesResponse() {
////        val resultResponse:MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()
////        assertEquals(resultResponse, notNullValue())
////        assertEquals(resultResponse, `is`(MutableLiveData<NetworkResult<FoodRecipe>>()))
////    }
////
////    @Test
////    fun testMainViewModel_callGetSearchedRecipesResponse() {
////        val resultResponse:MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()
////        assertEquals(resultResponse, notNullValue())
////        assertEquals(resultResponse, `is`(MutableLiveData<NetworkResult<FoodRecipe>>()))
////    }
//}