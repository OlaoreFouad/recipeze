package dev.olaore.recipeze.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.findNavController
import androidx.viewpager.widget.ViewPager

import dev.olaore.recipeze.R
import dev.olaore.recipeze.adapters.OnboardingAdapter
import dev.olaore.recipeze.databinding.FragmentOnboardingBinding
import kotlinx.android.synthetic.main.fragment_onboarding.*

class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var adapter: OnboardingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOnboardingBinding.inflate(layoutInflater)

        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = OnboardingAdapter(requireContext())
        binding.onboardingViewPager.adapter = adapter
        binding.currentIndex = 0

        binding.onboardingViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                pageSelected(position)
            }
        })

        binding.prevButton.setOnClickListener {
            binding.apply {
                when(currentIndex) {
                    0 -> onboardingViewPager.currentItem = 2
                    1 -> onboardingViewPager.currentItem = 0
                    2 -> onboardingViewPager.currentItem = 1
                }
            }
        }

        binding.nextButton.setOnClickListener {
            binding.apply {
                when(currentIndex) {
                    0 -> onboardingViewPager.currentItem = 1
                    1 -> onboardingViewPager.currentItem = 2
                }
            }
        }

        binding.goButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_onboardingFragment_to_registerFragment)
        }

        binding.topRightActionButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_onboardingFragment_to_registerFragment)
        }

    }

    private fun pageSelected(selectedIndex: Int) {
        binding.currentIndex = selectedIndex

        if (selectedIndex != 2) {
            setDefaultConstraints()
        } else {
            val constraintSet = ConstraintSet()
            constraintSet.clone(onboarding_layout)
            constraintSet.connect(R.id.prev_button, ConstraintSet.END, R.id.go_button, ConstraintSet.START)
            constraintSet.applyTo(onboarding_layout)
        }
    }

    private fun setDefaultConstraints() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(onboarding_layout)
        constraintSet.connect(R.id.prev_button, ConstraintSet.END, R.id.next_button, ConstraintSet.START)
        constraintSet.applyTo(onboarding_layout)
    }

}
