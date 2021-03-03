package dev.olaore.recipeze.fragments.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import dev.olaore.recipeze.databinding.FragmentRecipeDetailsBinding
import dev.olaore.recipeze.obtainParentViewModel
import dev.olaore.recipeze.viewmodels.RecipeViewModel

class RecipeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailsBinding
    private lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeDetailsBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = obtainParentViewModel(RecipeViewModel::class.java)

        viewModel.details.observe(viewLifecycleOwner) {
            prepareSummary(it.summary)
            prepareSourceUrl(it.sourceUrl)
            prepareSourceName(it.sourceName)
            prepareDishTypes(it.dishTypes)
            prepareLicense(it.license)
            prepareOccasions(it.occassions)
        }

    }

    private fun prepareOccasions(occasions: String?) {
        TODO("Not yet implemented")
    }

    private fun prepareLicense(license: String?) {
        TODO("Not yet implemented")
    }

    private fun prepareDishTypes(dishTypes: String?) {
        TODO("Not yet implemented")
    }

    private fun prepareSourceName(sourceName: String?) {
        TODO("Not yet implemented")
    }

    private fun prepareSourceUrl(sourceUrl: String?) {
        TODO("Not yet implemented")
    }

    private fun prepareSummary(summary: String) {
        TODO("Not yet implemented")
    }

}