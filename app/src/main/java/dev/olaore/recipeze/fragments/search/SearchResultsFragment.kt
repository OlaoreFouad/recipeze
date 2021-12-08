package dev.olaore.recipeze.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dev.olaore.recipeze.adapters.RecipesAdapter
import dev.olaore.recipeze.databinding.FragmentSearchResultsBinding
import dev.olaore.recipeze.models.mappers.toDomainRecipes

class SearchResultsFragment : Fragment() {

    private var _binding: FragmentSearchResultsBinding? = null
    val binding: FragmentSearchResultsBinding
        get() = _binding!!

    private val args: SearchResultsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSearchResultsBinding
            .inflate(inflater, container, false)
            .also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val _adapter = RecipesAdapter(requireContext())
        binding.searchResultsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = _adapter
        }

        _adapter.submitList(args.searchResultsContainer.results.toDomainRecipes())

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}