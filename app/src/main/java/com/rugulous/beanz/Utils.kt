package com.rugulous.beanz

import android.view.View
import android.view.animation.AccelerateInterpolator

class Utils {
    companion object {
        fun fadeIn(v: View, callback: Runnable? = null, duration: Long = 500) {
            v.alpha = 0f
            v.visibility = View.VISIBLE

            val animator = v.animate().alpha(1.0f).setDuration(duration).setInterpolator(AccelerateInterpolator())
            if(callback != null){
                animator.withEndAction(callback)
            }
            animator.start()
        }

        fun fadeOut(v: View, callback: Runnable? = null, duration: Long = 500){
            v.animate().alpha(0f).setDuration(duration).setInterpolator(AccelerateInterpolator()).withEndAction {
                v.visibility = View.GONE
                callback?.run()
            }.start()
        }

        fun crossFade(start: View, end: View, totalDuration: Long = 500){
            val splitDuration = totalDuration / 2

            fadeOut(end, {
                fadeIn(start, duration = splitDuration)
            }, splitDuration)
        }
    }
}