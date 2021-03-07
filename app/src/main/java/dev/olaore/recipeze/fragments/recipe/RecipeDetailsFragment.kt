package dev.olaore.recipeze.fragments.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import dev.olaore.recipeze.R
import dev.olaore.recipeze.databinding.FragmentRecipeDetailsBinding
import dev.olaore.recipeze.obtainParentViewModel
import dev.olaore.recipeze.viewmodels.RecipeViewModel
import java.util.*

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
            prepareSourceUrl(it.sourceUrl)
            prepareSourceName(it.sourceName)
            prepareDishTypes(it.dishTypes)
            prepareLicense(it.license)
            prepareOccasions(it.occassions)
        }

    }

    private fun prepareOccasions(occasions: String?) {
        occasions?.let {
            var _occasions = occasions?.split(", ")
            _occasions?.map {
                it.capitalize()
            }
            binding.detailsOccassions.text = resources.getString(
                R.string.occasion,
                _occasions?.joinToString()
            )
        }
        if (occasions.isNullOrEmpty()) {
            binding.detailsOccassions.text = resources.getString(
                R.string.occasion,
                "NONE"
            )
        }
    }

    private fun prepareLicense(license: String?) {
        binding.detailsSourceLicense.text = resources.getString(
            R.string.license, license?.toUpperCase()
        )
    }

    private fun prepareDishTypes(dishTypes: String?) {
        dishTypes?.let {
            var _dishTypes = dishTypes?.split(", ")
            var newDishTypes = _dishTypes.map {
                it.capitalize()
            }
            binding.detailsDishTypes.text = resources.getString(
                R.string.dish_types, newDishTypes.joinToString()
            )
        }
        if (dishTypes.isNullOrEmpty()) {
            binding.detailsDishTypes.text = resources.getString(
                R.string.dish_types, "NONE"
            )
        }
    }

    private fun prepareSourceName(sourceName: String?) {
        binding.detailsSourceName.text = resources.getString(
            R.string.source_name, sourceName
        )
    }

    private fun prepareSourceUrl(sourceUrl: String?) {
        binding.detailsSourceUrl.text = resources.getString(
            R.string.source_url, sourceUrl
        )
    }

}