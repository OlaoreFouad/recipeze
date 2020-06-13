package dev.olaore.recipeze.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.olaore.recipeze.R

import dev.olaore.recipeze.adapters.PreferencesAdapter
import dev.olaore.recipeze.databinding.FragmentPreferencesBinding
import dev.olaore.recipeze.listeners.OnPreferenceInteraction
import dev.olaore.recipeze.models.domain.Preference
import dev.olaore.recipeze.utils.Constants
import dev.olaore.recipeze.viewmodels.PreferencesViewModel

/**
 * A simple [Fragment] subclass.
 */
class PreferencesFragment : Fragment() {

    private lateinit var binding: FragmentPreferencesBinding
    private lateinit var viewModel: PreferencesViewModel
    private lateinit var preferencesAdapter: PreferencesAdapter
    private val TAG = "PreferencesFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreferencesBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(PreferencesViewModel::class.java)

        binding.lifecycleOwner = this
        binding.preferenceViewModel = viewModel

        preferencesAdapter = PreferencesAdapter(requireActivity(),
            object : OnPreferenceInteraction {
                override fun onItemClicked(position: Int) {
                    viewModel.onPreferenceSelected(position)
                }

                override fun onItemMoreClicked(position: Int) {
                    Log.d(TAG, "More Item at position: ${ position }")
                }
            })

        //  Set up the recyclerview
        binding.preferencesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = preferencesAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initialize the current preference
        viewModel.currentPreference.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                setUpPreferences(it)
            }
        })

    }

    private fun setUpPreferences(currentPreferenceId: String) {
        if (currentPreferenceId == Constants.DIETS_PREFERENCE) {
            viewModel.diets.observe(viewLifecycleOwner, Observer {
                preferencesAdapter.submitList(it.toMutableList())
                preferencesAdapter.notifyDataSetChanged()
            })
        } else if(currentPreferenceId == Constants.CUISINES_PREFERENCE) {
            viewModel.cuisines.observe(viewLifecycleOwner, Observer {
                preferencesAdapter.submitList(it.toMutableList())
                preferencesAdapter.notifyDataSetChanged()
            })
        }
    }

}