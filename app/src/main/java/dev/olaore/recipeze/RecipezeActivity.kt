package dev.olaore.recipeze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recipeze.*

class RecipezeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipeze)

        setSupportActionBar(recipeze_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        title = ""
    }
}
