package dev.olaore.recipeze.utils

import android.view.HapticFeedbackConstants
import android.view.View

fun vibrate(view: View) {
    view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
}