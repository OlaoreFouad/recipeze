package dev.olaore.recipeze.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dev.olaore.recipeze.R
import dev.olaore.recipeze.databinding.ActivitySearchBinding
import dev.olaore.recipeze.obtainViewModel
import dev.olaore.recipeze.viewmodels.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        searchViewModel = obtainViewModel(SearchViewModel::class.java)

        searchViewModel.user.observe(this, Observer {
            binding.searchSalutation.apply {
                text = Html.fromHtml(
                    resources.getString(R.string.hi_fouad_what_recipes_are_you_searching_for_today,
                    it.username ?: "user")
                ).toString()
            }
        })

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val text = p0.toString()
                if (text.isEmpty()) {
                    binding.searchLayout.transitionToStart()
                } else {
                    binding.searchLayout.transitionToEnd()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        binding.close.setOnClickListener {
            finish()
        }

    }
}