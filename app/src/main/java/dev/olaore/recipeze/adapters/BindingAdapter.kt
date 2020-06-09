package dev.olaore.recipeze.adapters

import android.view.View
import android.widget.Button
import androidx.databinding.BindingAdapter
import dev.olaore.recipeze.models.domain.Preference

@BindingAdapter("saveEnabled")
fun saveEnabled(view: Button, currentPreferences: List<Preference>?) {

    currentPreferences?.let {

        val selectedPreferences = it.filter { preference -> preference.isSelected }
        view.visibility = if (selectedPreferences.isEmpty()) View.GONE else View.VISIBLE

    }

}