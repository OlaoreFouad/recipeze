package dev.olaore.recipeze.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
    }
}
