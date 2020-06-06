package dev.olaore.recipeze.adapters

import android.view.View
import android.widget.Button
import androidx.databinding.BindingAdapter
import dev.olaore.recipeze.models.domain.Preference

@BindingAdapter("saveEnabled")
fun saveEnabled(view: Button, currentPreferences: List<Preference>?) {

    currentPreferences?.let {
        view.visibility = if (currentPreferences.isEmpty()) View.VISIBLE else View.GONE
    }

}