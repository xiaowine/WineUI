package cn.xiaowine.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.TypedValue
import android.view.View
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

    fun createRoundShape(radius: Float, color: Int): ShapeDrawable {
        val radii = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
        val roundRectShape = RoundRectShape(radii, null, null)
        return ShapeDrawable(roundRectShape).apply {
            paint.color = color
        }
    }

    @SuppressLint("PrivateApi")
    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun getProp(mKey: String): String = Class.forName("android.os.SystemProperties").getMethod("get", String::class.java).invoke(Class.forName("android.os.SystemProperties"), mKey).toString()
}