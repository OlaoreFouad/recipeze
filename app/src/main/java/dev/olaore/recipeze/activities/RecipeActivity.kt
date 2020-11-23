package dev.olaore.recipeze.activities

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.widget.NestedScrollView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dev.olaore.recipeze.R

class RecipeActivity : AppCompatActivity() {

    lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    lateinit var bottomSheet: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

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
}
