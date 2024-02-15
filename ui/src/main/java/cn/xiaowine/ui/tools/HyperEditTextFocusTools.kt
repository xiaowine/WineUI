package cn.xiaowine.ui.tools

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import cn.xiaowine.ui.appcompat.HyperEditText

object HyperEditTextFocusTools {
    fun isShouldHideKeyboard(v: View, event: MotionEvent): Boolean {
        if (v is HyperEditText) {
            val l = intArrayOf(0, 0)
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.getHeight()
            val right = left + v.getWidth()
            return (!(event.x > left) || !(event.x < right) || !(event.y > top) || !(event.y < bottom))
        }
        return false
    }

    fun hideKeyboardAndClearFocus(context: Context, editText: HyperEditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
        editText.cancelAnimation()
    }

    fun Context.touchIfNeedHideKeyboard(currentFocus: View?, event: MotionEvent, default: () -> Boolean): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus ?: return default()
            if (isShouldHideKeyboard(v, event)) {
                hideKeyboardAndClearFocus(this, v as HyperEditText)
            }
        }
        return default()
    }
}