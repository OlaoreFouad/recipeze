package dev.olaore.recipeze.fragments.search

import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.olaore.recipeze.R
import dev.olaore.recipeze.adapters.RecentSearchesAdapter
import dev.olaore.recipeze.databinding.FragmentSearchBinding
import dev.olaore.recipeze.listeners.OnRecentSearchInteraction
import dev.olaore.recipeze.models.domain.RecentSearch
import dev.olaore.recipeze.models.domain.SearchResultsContainer
import dev.olaore.recipeze.models.mappers.Status
import dev.olaore.recipeze.obtainViewModel
import dev.olaore.recipeze.showToast
import dev.olaore.recipeze.viewmodels.SearchViewModel


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding!!

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var recentSearchesAdapter: RecentSearchesAdapter

    private lateinit var query: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSearchBinding.inflate(
            inflater, container, false
        ).also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel = obtainViewModel(SearchViewModel::class.java)
        searchViewModel.searchedRecipes.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                showLoader(it.status == Status.LOADING)
                when (it.status) {
                    Status.ERROR -> {
                        showToast(it.message!!)
                    }
                    Status.SUCCESS -> {
                        val results = it.data!!
                        if (results.isNotEmpty()) {
                            resetSearchText()
                            val searchResultsContainer = SearchResultsContainer(query, results)
                            findNavController().navigate(
                                SearchFragmentDirections.actionSearchFragmentToSearchResultsFragment(
                                    searchResultsContainer
                                )
                            )
                        } else {
                            showToast("There are no recipes for: " + this.query)
                        }
                    }
                }
            }
        })

        searchViewModel.recentSearches.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                displayRecentSearches(it)
            } else {
                displayRecentSearches(listOf())
            }
        })

        searchViewModel.user.observe(viewLifecycleOwner, Observer {
            binding.searchSalutation.apply {
                text = Html.fromHtml(
                    resources.getString(
                        R.string.hi_fouad_what_recipes_are_you_searching_for_today,
                        it.username ?: "user"
                    )
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
            requireActivity().finish()
        }


    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
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
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = recentSearchesAdapter
        }
        recentSearchesAdapter.submitList(searches)
    }

    private fun performSearch() {
        this.query = binding.searchEditText.text.toString()
        searchViewModel.search(this.query)
    }

    private fun showLoader(show: Boolean = true) {
        binding.searchRecipesLoader.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun resetSearchText() {
        binding.searchEditText.setText("")
    }

}