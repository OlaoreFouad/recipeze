package dev.olaore.recipeze.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dev.olaore.recipeze.R
import dev.olaore.recipeze.databinding.FragmentPinBinding

class PinFragment : Fragment() {

    private lateinit var binding: FragmentPinBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPinBinding.inflate(inflater)

        return binding.root
    }

}
