package dev.olaore.recipeze.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import dev.olaore.recipeze.R
import dev.olaore.recipeze.RecipezeActivity
import dev.olaore.recipeze.adapters.OnPinButtonClicked
import dev.olaore.recipeze.adapters.PinButtonAdapter
import dev.olaore.recipeze.databinding.FragmentPinBinding
import dev.olaore.recipeze.obtainViewModel
import dev.olaore.recipeze.utils.vibrate
import dev.olaore.recipeze.viewmodels.PinViewModel
import kotlinx.coroutines.*

class PinFragment : Fragment(), OnPinButtonClicked {

    private lateinit var binding: FragmentPinBinding
    private lateinit var pinAdapter: PinButtonAdapter
    private var typedPin: String = ""

    private lateinit var viewModel: PinViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPinBinding.inflate(inflater)
        binding.currentPin = typedPin
        viewModel = obtainViewModel(PinViewModel::class.java)

        pinAdapter = PinButtonAdapter(this)
        viewModel.user.observe(viewLifecycleOwner, Observer {
            it.let {
                viewModel.pin = it.pin!!
            }
        })
        CoroutineScope(Dispatchers.IO).launch {
            reInitializePin(viewModel.providedPin)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pinButtonsRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@PinFragment.context, 3)
            adapter = pinAdapter
        }
    }

    override fun onClick(value: String, isBackspace: Boolean) {

        if (isBackspace && typedPin.isNotEmpty()) {
            typedPin = if (typedPin.length == 1) {
                ""
            } else {
                typedPin.substring(0, typedPin.length - 1)
            }
            binding.currentPin = typedPin
            viewModel.providedPin = typedPin
            return
        } else if (isBackspace && typedPin.isEmpty()) {
            return
        }

        typedPin += value
        binding.currentPin = typedPin
        viewModel.providedPin = typedPin
        if (typedPin.length == 4) {
            CoroutineScope(Dispatchers.IO).launch {
                checkPin()
            }
        }
    }

    private suspend fun checkPin() {
        delay(750)
        Log.d("PinFragment", "${ viewModel.pin } ${ viewModel.providedPin }")
        if (viewModel.isEqual()) {
            navigate()
        } else {
            vibrate(requireView())
            reInitializePin("")
        }
    }

    private suspend fun navigate() {
        withContext(Dispatchers.Main) {
            startActivity(Intent(requireActivity(), RecipezeActivity::class.java))
            requireActivity().finish()
        }
    }

    private suspend fun reInitializePin(value: String) {
        withContext(Dispatchers.Main) {
            typedPin = value
            binding.currentPin = value
            viewModel.providedPin = value
        }
    }

}
