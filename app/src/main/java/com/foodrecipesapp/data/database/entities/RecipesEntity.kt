package com.foodrecipesapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.foodrecipesapp.models.FoodRecipe
import com.foodrecipesapp.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}