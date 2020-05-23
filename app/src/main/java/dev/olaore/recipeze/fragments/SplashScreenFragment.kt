package dev.olaore.recipeze.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController

import dev.olaore.recipeze.R

class SplashScreenFragment : Fragment() {

    val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler.postDelayed({
            findNavController(requireParentFragment()).navigate(
                SplashScreenFragmentDirections.actionSplashScreenFragmentToOnboardingFragment()
            )
        }, 2500)

        // TODO: add animations for the centered "logo" in splash screen layout
    }

}
