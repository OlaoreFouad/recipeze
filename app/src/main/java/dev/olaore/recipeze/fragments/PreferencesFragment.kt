package dev.olaore.recipeze.fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.olaore.recipeze.R
import dev.olaore.recipeze.RecipezeActivity

import dev.olaore.recipeze.adapters.PreferencesAdapter
import dev.olaore.recipeze.databinding.FragmentPreferencesBinding
import dev.olaore.recipeze.listeners.OnPreferenceInteraction
import dev.olaore.recipeze.models.domain.Preference
import dev.olaore.recipeze.models.domain.User
import dev.olaore.recipeze.obtainViewModel
import dev.olaore.recipeze.utils.Constants
import dev.olaore.recipeze.utils.Prefs
import dev.olaore.recipeze.viewmodels.PreferencesViewModel

/**
 * A simple [Fragment] subclass.
 */
class PreferencesFragment : Fragment() {

    private lateinit var binding: FragmentPreferencesBinding
    private lateinit var viewModel: PreferencesViewModel
    private lateinit var preferencesAdapter: PreferencesAdapter
    private lateinit var preferenceMoreDialog: Dialog
    private val TAG = "PreferencesFragment"

    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreferencesBinding.inflate(layoutInflater)
        viewModel = obtainViewModel(PreferencesViewModel::class.java)

        binding.lifecycleOwner = this
        binding.preferenceViewModel = viewModel

        user = PreferencesFragmentArgs.fromBundle(requireArguments()).user
        viewModel.patchUser(user)

        preferencesAdapter = PreferencesAdapter(requireActivity(),
            object : OnPreferenceInteraction {
                override fun onItemClicked(position: Int) {
                    viewModel.onPreferenceSelected(position)
                }

                override fun onItemMoreClicked(position: Int) {
                    val preference = preferencesAdapter.currentList[position]
                    showDialog(preference)
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

//        listen for changes in the registration status, if this returns true, navigate to the main screen and close this very one and every other
//        fragment in the back stack
        viewModel.registrationStatus.observe(viewLifecycleOwner, Observer {
            if (it) {
                Prefs.authenticateUser(requireContext(), viewModel.user.pin!!)
                startActivity(Intent(requireActivity(), RecipezeActivity::class.java))
                requireActivity().finish()
            }
        })

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

    private fun showDialog(preference: Preference) {
        preferenceMoreDialog = Dialog(requireContext())
        preferenceMoreDialog.setContentView(R.layout.popup_preference_details)

        val closeButton = preferenceMoreDialog.findViewById<ImageView>(R.id.close_dialog_image)
        val titleText = preferenceMoreDialog.findViewById<TextView>(R.id.preference_details_title)
        val shalayeText = preferenceMoreDialog.findViewById<TextView>(R.id.preference_details_shalaye)

        titleText.text = preference.name
        shalayeText.text = preference.details

        closeButton.setOnClickListener { preferenceMoreDialog.dismiss() }
        preferenceMoreDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        preferenceMoreDialog.show()

    }

}