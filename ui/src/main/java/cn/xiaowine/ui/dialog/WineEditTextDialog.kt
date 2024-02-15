package cn.xiaowine.ui.dialog

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.view.MotionEvent
import cn.xiaowine.ui.appcompat.HyperEditText
import cn.xiaowine.ui.data.HyperEditBackgroundStyle
import cn.xiaowine.ui.tools.HyperEditTextFocusTools.touchIfNeedHideKeyboard

class WineEditTextDialog(context: Context) : WineDialog(context) {

    val editText: HyperEditText = HyperEditText(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addCustomizeView(editText)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        return context.touchIfNeedHideKeyboard(currentFocus, event) {
            super.dispatchTouchEvent(event)
        }
    }

    fun setBackgroundStyle(backgroundStyle: HyperEditBackgroundStyle?) {
        editText.setBackgroundStyle(backgroundStyle)
    }

    fun getValue(): Editable {
        return editText.text ?: SpannableStringBuilder("")
    }
}