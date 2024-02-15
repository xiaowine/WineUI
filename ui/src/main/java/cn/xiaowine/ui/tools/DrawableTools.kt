package cn.xiaowine.ui.tools

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape

object DrawableTools {
    fun createRoundShape(radius: Float, color: Int): ShapeDrawable {
        val radii = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
        val roundRectShape = RoundRectShape(radii, null, null)
        return ShapeDrawable(roundRectShape).apply {
            paint.color = color
        }
    }

    fun createRoundShape(radius: FloatArray, color: Int): ShapeDrawable {
        val radii = floatArrayOf(radius[0], radius[0], radius[1], radius[1], radius[2], radius[2], radius[3], radius[3])
        val roundRectShape = RoundRectShape(radii, null, null)
        return ShapeDrawable(roundRectShape).apply {
            shape = roundRectShape
            paint.color = color
        }
    }
}