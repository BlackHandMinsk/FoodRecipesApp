package com.foodrecipesapp.util

import androidx.recyclerview.widget.DiffUtil
import com.foodrecipesapp.models.Result

class RecipesDiffUtil (private val oldList:List<com.foodrecipesapp.models.Result>,private val newList: List<Result>):
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
       return oldListSize
    }

    override fun getNewListSize(): Int {
       return newListSize
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return oldList[oldItemPosition]===newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition]==newList[newItemPosition]
    }
}