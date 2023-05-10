package com.rugulous.beanz

import android.view.View
import android.view.animation.AccelerateInterpolator

class Utils {
    companion object {
        fun fadeIn(v: View, duration: Long = 500) {
            v.alpha = 0f
            v.animate().alpha(1.0f).setDuration(duration).setInterpolator(AccelerateInterpolator()).start()
            v.visibility = View.VISIBLE
        }
    }
}