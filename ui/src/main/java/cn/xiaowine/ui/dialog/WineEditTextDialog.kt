package cn.xiaowine.ui.dialog

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import cn.xiaowine.ui.appcompat.HyperEditText

class WineEditTextDialog(context: Context) : WineDialog(context) {
    val editText: HyperEditText = HyperEditText(context)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addCustomizeView(editText)
    }

    fun getValue(): Editable {
        return editText.text ?: SpannableStringBuilder("")
    }
}