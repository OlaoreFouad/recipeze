package dev.olaore.recipeze.adapters

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import dev.olaore.recipeze.R

class OnboardingAdapter(private val context: Context) : PagerAdapter() {

    private val items = listOf(
        OnboardingItem("Become your own chef", "Become", R.drawable.chef, R.string.onboarding_desc_one),
        OnboardingItem("Choose from a range of healthy options", "Choose", R.drawable.options,
            R.string.onboarding_desc_two),
        OnboardingItem("Tailor what you see - and eat!", "Tailor", R.drawable.customise,
            R.string.onboarding_desc_three)
    )

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val onboardingViewGroup = layoutInflater.inflate(
            R.layout.item_onboarding, container, false
        )

        val currentItem = items[position]
        val image = onboardingViewGroup.findViewById<ImageView>(R.id.onboarding_image)
        val title = onboardingViewGroup.findViewById<TextView>(R.id.onboarding_title)
        val description = onboardingViewGroup.findViewById<TextView>(R.id.onboarding_content)

        image.setImageResource(currentItem.image)

        val spanned = SpannableStringBuilder(currentItem.title)
        spanned.setSpan(
            ForegroundColorSpan(
                context.resources.getColor(R.color.colorPrimaryDark)
            ), 0, currentItem.span.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        title.text = spanned
        description.text = context.resources.getString(currentItem.description)

        container.addView(onboardingViewGroup)

        return onboardingViewGroup
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view as ConstraintLayout === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

    override fun getCount(): Int {
        return items.size
    }

}

data class OnboardingItem(
    var title: String,
    var span: String,
    @DrawableRes  var image: Int,
    @StringRes var description: Int
)