package cn.xiaowine.ui.tools

import android.annotation.SuppressLint
import android.content.res.Resources.getSystem
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Environment
import android.util.TypedValue
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

object Tools {
    val Int.dp: Int get() = (this.toFloat().dp).toInt()

    val Float.dp: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, getSystem().displayMetrics)

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


    fun getProp(name: String): String {
        var prop = getPropByShell(name)
        if (prop.isEmpty()) prop = getPropByStream(name)
        if (prop.isEmpty()) prop = getPropByReflect(name)
        return prop
    }

    private fun getPropByShell(propName: String): String {
        return try {
            val p = Runtime.getRuntime().exec("getprop $propName")
            BufferedReader(InputStreamReader(p.inputStream), 1024).use { it.readLine() ?: "" }
        } catch (_: Exception) {
            ""
        }
    }

    private fun getPropByStream(key: String): String {
        return try {
            val prop = Properties()
            FileInputStream(File(Environment.getRootDirectory(), "build.prop")).use { prop.load(it) }
            prop.getProperty(key, "")
        } catch (_: Exception) {
            ""
        }
    }

    private fun getPropByReflect(key: String): String {
        return try {
            @SuppressLint("PrivateApi") val clz = Class.forName("android.os.SystemProperties")
            val getMethod = clz.getMethod("get", String::class.java, String::class.java)
            getMethod.invoke(clz, key, "") as String
        } catch (_: Exception) {
            ""
        }
    }
}