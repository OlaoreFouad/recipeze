package dev.olaore.recipeze.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.olaore.recipeze.R
import dev.olaore.recipeze.adapters.RecentSearchesAdapter
import dev.olaore.recipeze.databinding.ActivitySearchBinding
import dev.olaore.recipeze.listeners.OnRecentSearchInteraction
import dev.olaore.recipeze.models.domain.RecentSearch
import dev.olaore.recipeze.models.mappers.Status
import dev.olaore.recipeze.obtainViewModel
import dev.olaore.recipeze.showToast
import dev.olaore.recipeze.viewmodels.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var recentSearchesAdapter: RecentSearchesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.isLoading = false

        searchViewModel = obtainViewModel(SearchViewModel::class.java)
        searchViewModel.searchedRecipes.observe(this, Observer {

            binding.isLoading = it.status == Status.LOADING
            when (it.status) {
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                Status.SUCCESS -> {
                    Log.d("SearchActivity", "Search Results: ${ it.data?.size }")
                }
            }

        })

        searchViewModel.recentSearches.observe(this, Observer {
            showToast("Size: ${ it.size }")
            if (it.isNotEmpty()) {
                displayRecentSearches(it)
            } else {
                displayRecentSearches(listOf())
            }
        })

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

        binding.searchButton.setOnClickListener {
            performSearch()
        }

        binding.close.setOnClickListener {
            finish()
        }

    }

    private fun displayRecentSearches(searches: List<RecentSearch>) {
        recentSearchesAdapter = RecentSearchesAdapter(object : OnRecentSearchInteraction {

            override fun onSelectSearch(query: String) {
                binding.searchEditText.setText(query)
                binding.searchButton.performClick()
            }

            override fun onDeleteSearch(id: String) {
                searchViewModel.deleteRecentSearch(id)
            }

        })
        binding.recentSearchesList.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            setHasFixedSize(true)
            adapter = recentSearchesAdapter
        }
        recentSearchesAdapter.submitList(searches)
    }

    private fun performSearch() {
        val query = binding.searchEditText.text.toString()
        searchViewModel.search(query)
    }
}