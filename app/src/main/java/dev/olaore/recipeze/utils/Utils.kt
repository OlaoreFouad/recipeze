package dev.olaore.recipeze.utils

import android.view.HapticFeedbackConstants
import android.view.View

fun vibrate(view: View) {
    view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
}

object Utils {

    const val RECIPE_ID_KEY = "RECIPE_ID"
    const val BASE_URL = "https://api.spoonacular.com/recipes/"
//    add your API_KEY here
    const val API_KEY = "eb628949d598445b993e2f98d75e22ad"

    @JvmStatic
    fun addQuote(minutes: String?) = "$minutes '"

}