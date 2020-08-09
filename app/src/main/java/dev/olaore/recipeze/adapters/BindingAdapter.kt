package dev.olaore.recipeze.adapters

import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import dev.olaore.recipeze.models.domain.Preference

@BindingAdapter("saveEnabled")
fun saveEnabled(view: Button, currentPreferences: List<Preference>?) {

    currentPreferences?.let {

        val selectedPreferences = it.filter { preference -> preference.isSelected }
        view.visibility = if (selectedPreferences.isEmpty()) View.GONE else View.VISIBLE

    }

}

@BindingAdapter("imageUrl")
fun imageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("readyIn")
fun readyIn(view: TextView, value: Int) {
    view.text = "Ready In: $value minutes"
}