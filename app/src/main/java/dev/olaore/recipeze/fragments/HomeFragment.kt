package dev.olaore.recipeze.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip

import dev.olaore.recipeze.R
import dev.olaore.recipeze.adapters.RecipesAdapter
import dev.olaore.recipeze.databinding.FragmentHomeBinding
import dev.olaore.recipeze.models.domain.Recipe
import dev.olaore.recipeze.obtainViewModel
import dev.olaore.recipeze.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var cuisines: Array<String>
    private lateinit var recipesAdapter: RecipesAdapter

    private val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater)

        viewModel = obtainViewModel(HomeViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.user.observe(viewLifecycleOwner, Observer {
            cuisines = it.cuisines.split(",").toTypedArray()
            setUpChips()
        })

        viewModel.randomRecipes.observe(viewLifecycleOwner, Observer {
            if (it !== null) {
                setUpRecipes(it)
            }
        })

    }

    private fun setUpRecipes(recipes: List<Recipe>) {
        recipesAdapter = RecipesAdapter(requireContext())
        recipes_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipesAdapter
        }
        recipesAdapter.submitList(recipes)
        Log.d(TAG, recipesAdapter.currentList.size.toString())
    }

    private fun setUpChips() {
        recipes_chip_group.isSingleSelection = true
        val allChip = layoutInflater.inflate(R.layout.single_chip_layout, recipes_chip_group, false) as Chip
        allChip.text = resources.getString(R.string.all_text)
        allChip.isCheckable = true
        allChip.id = 0
        allChip.isClickable = true

        recipes_chip_group.addView(allChip)

        cuisines.forEachIndexed { index, it ->
            val chip = layoutInflater.inflate(R.layout.single_chip_layout, recipes_chip_group, false) as Chip
            chip.text = it
            chip.isCheckable = true
            chip.isClickable = true
            chip.id = index + 1
            recipes_chip_group.addView(chip)
        }

        recipes_chip_group.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == 0) {
                viewModel.getRandomRecipes("ALL")
                return@setOnCheckedChangeListener
            }
            viewModel.getRandomRecipes(cuisines[checkedId - 1])
        }

        recipes_chip_group.check(0)

    }


}
