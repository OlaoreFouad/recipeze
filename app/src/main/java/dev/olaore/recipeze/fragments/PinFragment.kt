package dev.olaore.recipeze.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import dev.olaore.recipeze.R
import dev.olaore.recipeze.adapters.OnPinButtonClicked
import dev.olaore.recipeze.adapters.PinButtonAdapter
import dev.olaore.recipeze.databinding.FragmentPinBinding
import dev.olaore.recipeze.obtainViewModel
import dev.olaore.recipeze.viewmodels.PinViewModel

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

            if (typedPin.length == 1) {
                typedPin = ""
            } else {
                typedPin = typedPin.substring(0, typedPin.length - 1)
            }

            binding.currentPin = typedPin
            return
        } else if (isBackspace && typedPin.isEmpty()) {
            return
        }

        typedPin += value
        binding.currentPin = typedPin
        if (typedPin.length == 4) {
            checkPin()
            typedPin = ""
            binding.currentPin = ""
        }
    }

    private fun checkPin() {
        // do pin checking here
    }

}
