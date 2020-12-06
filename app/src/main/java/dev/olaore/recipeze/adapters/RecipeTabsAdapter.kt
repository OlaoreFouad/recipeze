package dev.olaore.recipeze.adapters

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class RecipeTabsAdapter(
    private val fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val titles = listOf("Ingredients", "Instructions", "Details")
    private val fragments = mutableListOf<Fragment>()

    override fun getCount() = titles.size

    override fun getItem(position: Int) = fragments[position]

    override fun getPageTitle(position: Int) = titles[position]

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

}