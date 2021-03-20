package dev.olaore.recipeze.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.databinding.DataBindingUtil
import dev.olaore.recipeze.R
import dev.olaore.recipeze.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        binding.searchSalutation.apply {
            text = Html.fromHtml(
                resources.getString(R.string.hi_fouad_what_recipes_are_you_searching_for_today)
            ).toString()
        }

        binding.close.setOnClickListener {
            finish()
        }

    }
}