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
import dev.olaore.recipeze.models.domain.Preference
import dev.olaore.recipeze.viewmodels.PreferencesViewModel

/**
 * A simple [Fragment] subclass.
 */
class PreferencesFragment : Fragment() {

    private lateinit var binding: FragmentPreferencesBinding
    private lateinit var viewModel: PreferencesViewModel
    private lateinit var adapter: PreferencesAdapter
    private val TAG = "PreferencesFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreferencesBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(PreferencesViewModel::class.java)
        binding.lifecycleOwner = this

        adapter = PreferencesAdapter(requireActivity())

        binding.preferencesRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = adapter
        }

        viewModel.diets.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "Size: ${ it.size }")
            adapter.submitList(it as MutableList<Preference>)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
