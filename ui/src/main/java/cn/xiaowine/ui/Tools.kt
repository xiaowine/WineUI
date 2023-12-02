package cn.xiaowine.ui

import android.content.Context
import android.text.TextUtils
import android.util.TypedValue
import android.view.Display
import android.view.View
import android.view.WindowManager

object Tools {
    fun dp2px(context: Context, dpValue: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.resources.displayMetrics).toInt()

    fun getDisplay(context: Context): Display {
        return (context.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    }

    fun isRtl(context: Context): Boolean {
        return TextUtils.getLayoutDirectionFromLocale(context.resources.configuration.locale) == View.LAYOUT_DIRECTION_RTL
    }
    fun View.findViewByIdOrNull(id: Int): View? {
        return findViewById(id)
    }
}