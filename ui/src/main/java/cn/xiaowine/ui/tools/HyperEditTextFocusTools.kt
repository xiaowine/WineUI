package cn.xiaowine.ui.tools

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager
import cn.xiaowine.ui.appcompat.HyperEditText

object HyperEditTextFocusTools {

    private fun isShouldHideKeyboard(v: View, event: MotionEvent): Boolean {
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
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            editText.windowInsetsController?.hide(WindowInsets.Type.ime())
        } else {
            val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken, 0)
        }
        editText.apply {
            cancelAnimation()
            clearFocus()
        }
    }

    fun Context.touchIfNeedHideKeyboard(currentFocus: View?, event: MotionEvent, isDialog: Boolean, default: () -> Boolean): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            val v = currentFocus ?: return default()
            if (isShouldHideKeyboard(v, event)) {
                hideKeyboardAndClearFocus(this, v as HyperEditText)
                if (isDialog) return false
            }
        }
        return default()
    }
}