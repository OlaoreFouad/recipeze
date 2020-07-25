package dev.olaore.recipeze.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import dev.olaore.recipeze.R
import dev.olaore.recipeze.databinding.FragmentHomeBinding
import dev.olaore.recipeze.obtainViewModel
import dev.olaore.recipeze.viewmodels.HomeViewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

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
            Log.d(TAG, "$it")
        })

        viewModel.randomRecipes.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "Here?")
            Log.d(TAG, "Recipes Size: ${ it.recipes.size }")
        })

    }


}
