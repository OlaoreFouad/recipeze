package dev.olaore.recipeze.fragments

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment.findNavController

import dev.olaore.recipeze.R
import kotlinx.android.synthetic.main.fragment_splash_screen.*

class SplashScreenFragment : Fragment() {

    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animator: Animator = AnimatorInflater.loadAnimator(requireContext(), R.animator.shake)
        animator.apply {
            setTarget(app_logo)
            start()
        }

        val extras = FragmentNavigatorExtras(app_logo to "app_name")

        handler.postDelayed({
            findNavController(requireParentFragment()).navigate(
                SplashScreenFragmentDirections.actionSplashScreenFragmentToOnboardingFragment(), extras
            )
        }, 4500)
    }

}
