package dev.olaore.recipeze.models.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var username: String? = "",

    var pin: String? = "",

    var cuisines: String = "",

    var diets: String = ""
) : Parcelable