package com.foodrecipesapp.ui.fragments.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import coil.load
import com.foodrecipesapp.R
import com.foodrecipesapp.bindingadapters.RecipesRowBinding
import com.foodrecipesapp.models.Result
import com.foodrecipesapp.util.Constants.Companion.RECIPE_RESULT_KEY
import kotlinx.android.synthetic.main.fragment_overview.view.*


class OverviewFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_overview, container, false)

        val args = arguments
        val myBundle: Result = args!!.getParcelable<Result>(RECIPE_RESULT_KEY) as Result

        view.main_imageView.load(myBundle.image)
        view.title_textView.text = myBundle.title
        view.likes_textView.text = myBundle.aggregateLikes.toString()
        view.time_textView.text = myBundle.readyInMinutes.toString()

        RecipesRowBinding.parseHtml(view.summary_textView, myBundle.summary)

        updateColors(myBundle.vegetarian, view.vegetarian_textView, view.vegetarian_imageView)
        updateColors(myBundle.vegan, view.vegan_textView, view.vegan_imageView)
        updateColors(myBundle.cheap, view.cheap_textView, view.cheap_imageView)
        updateColors(myBundle.dairyFree, view.dairy_free_textView, view.dairy_free_imageView)
        updateColors(myBundle.glutenFree, view.gluten_free_textView, view.gluten_free_imageView)
        updateColors(myBundle.glutenFree, view.gluten_free_textView, view.gluten_free_imageView)
        updateColors(myBundle.veryHealthy, view.healthy_textView, view.healthy_imageView)

        return view
    }


    private fun updateColors(stateIsOn: Boolean, tv: TextView, iv: ImageView) {
        if (stateIsOn) {
            iv.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
    }
}