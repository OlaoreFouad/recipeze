package dev.olaore.recipeze.activities

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dev.olaore.recipeze.R
import dev.olaore.recipeze.adapters.RecipeTabsAdapter
import dev.olaore.recipeze.databinding.ActivityRecipeBinding
import dev.olaore.recipeze.fragments.recipe.RecipeDetailsFragment
import dev.olaore.recipeze.fragments.recipe.RecipeIngredientsFragment
import dev.olaore.recipeze.fragments.recipe.RecipeInstructionsFragment
import dev.olaore.recipeze.models.domain.Recipe
import dev.olaore.recipeze.models.mappers.Status
import dev.olaore.recipeze.obtainViewModel
import dev.olaore.recipeze.utils.Utils
import dev.olaore.recipeze.viewmodels.RecipeViewModel

class RecipeActivity : AppCompatActivity() {

    lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    lateinit var bottomSheet: View
    lateinit var recipeTabsAdapter: RecipeTabsAdapter
    lateinit var recipeViewModel: RecipeViewModel

    lateinit var binding: ActivityRecipeBinding
    var recipeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_recipe
        )

        recipeViewModel = obtainViewModel(RecipeViewModel::class.java)

        val extras = intent.extras
        recipeId = extras!!.getInt(Utils.RECIPE_ID_KEY)
        recipeViewModel.recipeId = recipeId

        setupBottomSheet()
        setupTabsWithViewPager()

        getRecipeDetails();

    }

    private fun setupBottomSheet() {
        bottomSheet = findViewById(R.id.recipe_bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams

        bottomSheetBehavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels / 2

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> Log.d("RecipeActivity", "Collapsed")
                    BottomSheetBehavior.STATE_DRAGGING -> Log.d("RecipeActivity", "Dragging")
                    BottomSheetBehavior.STATE_EXPANDED -> Log.d("RecipeActivity", "Expanded")
                    BottomSheetBehavior.STATE_SETTLING -> Log.d("RecipeActivity", "Settling")
                }
            }
        })
    }

    private fun setupTabsWithViewPager() {
        recipeTabsAdapter = RecipeTabsAdapter(supportFragmentManager)
        recipeTabsAdapter.apply {
            addFragment(RecipeIngredientsFragment())
            addFragment(RecipeInstructionsFragment())
            addFragment(RecipeDetailsFragment())
        }

        binding.recipeViewPager.adapter = recipeTabsAdapter
        binding.recipeTabLayout.setupWithViewPager(binding.recipeViewPager)
    }

    private fun getRecipeDetails() {
        recipeViewModel.recipe.observe(this) {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> Toast.makeText(applicationContext, "Recipe Details Loading", Toast.LENGTH_LONG).show()
                    Status.ERROR -> Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
                    Status.SUCCESS -> Log.d("RecipeActivity", it.data.toString())
                }
            }
        }

        recipeViewModel.retrieveRecipeDetails()
    }

}
