package com.foodrecipesapp.bindingadapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.foodrecipesapp.R
import com.foodrecipesapp.models.Result
import com.foodrecipesapp.ui.fragments.recipes.RecipesFragment
import com.foodrecipesapp.ui.fragments.recipes.RecipesFragmentDirections
import java.lang.Exception

class RecipesRowBinding {

    companion object{

        @BindingAdapter("onRecipeCLickListener")
        @JvmStatic
        fun onRecipeCLickListener(recipeRowLayout:ConstraintLayout,result: Result){
            recipeRowLayout.setOnClickListener {
                try {
                    val action = RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(result)
                    recipeRowLayout.findNavController().navigate(action)
                }catch (e:Exception){
                    Log.d("onRecipeClickListener",e.toString())
                }
            }
        }

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView,imageUrl:String){
            imageView.load(imageUrl){
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }

        @BindingAdapter("setNumberOfLikes")
        @JvmStatic
        fun setNumberOfLikes(textView: TextView,likes:Int){
            textView.text = likes.toString()
        }
        @BindingAdapter("setNumberOfMinutes")
        @JvmStatic
        fun setNumberOfMinutes(textView: TextView,minutes:Int){
            textView.text = minutes.toString()
        }


        @BindingAdapter("applyVeganColor")
        @JvmStatic
       fun applyVeganColor(view: View, vegan:Boolean){
           if(vegan){
               when(view){
                   is TextView->{
                       view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
                   }
                   is ImageView->{
                       view.setColorFilter(ContextCompat.getColor(view.context,R.color.green))
                   }
               }
           }
        }

    }
}