package cn.xiaowine.ui

import android.content.Context
import android.text.TextUtils
import android.util.TypedValue
import android.view.Display
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet

object Tools {
    fun dp2px(context: Context, dpValue: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.resources.displayMetrics).toInt()

    private fun getAlphaAnimation(into: Boolean, duration: Long = 300): AnimationSet {
        val alphaAnimation = (if (into) AlphaAnimation(0F, 1F) else AlphaAnimation(1F, 0F)).apply {
            this.duration = duration
        }
        return AnimationSet(true).apply {
            addAnimation(alphaAnimation)
        }
    }

    fun View.hideView(duration: Long = 300) {
        if (visibility == View.GONE) return
        val alphaAnimation = getAlphaAnimation(false, duration).apply {
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    visibility = View.GONE
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
        }
        startAnimation(alphaAnimation)
    }

    fun View.showView(duration: Long = 300) {
        if (visibility == View.VISIBLE) return
        val alphaAnimation = getAlphaAnimation(true, duration).apply {
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    visibility = View.VISIBLE
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
        }
        startAnimation(alphaAnimation)
    }
}