package cn.xiaowine.app.pages

import android.annotation.SuppressLint
import android.widget.LinearLayout
import cn.xiaowine.ui.appcompat.HyperEditText
import cn.xiaowine.ui.WinePage
import cn.xiaowine.ui.data.HyperEditBackgroundStyle
import cn.xiaowine.ui.tools.Tools

@SuppressLint("SetTextI18n")
class CustomPage : WinePage() {

    init {
        initPage {
            toPageText()
            title { text = "Custom" }
            custom(HyperEditText::class.java) {
                (it as HyperEditText).apply {
                    (layoutParams as LinearLayout.LayoutParams).apply {
                        setMargins(Tools.dp2px(context, 30f), Tools.dp2px(context, 10f), Tools.dp2px(context, 30f), Tools.dp2px(context, 10f))
                    }
                    hint = "请输入"
                }
            }
            custom(HyperEditText::class.java) {
                (it as HyperEditText).apply {
                    (layoutParams as LinearLayout.LayoutParams).apply {
                        setMargins(Tools.dp2px(context, 30f), Tools.dp2px(context, 10f), Tools.dp2px(context, 30f), Tools.dp2px(context, 10f))
                    }
                    isSingleLine = true
                    hint = "请输入"
                }
            }
            custom(HyperEditText::class.java) {
                (it as HyperEditText).apply {
                    (layoutParams as LinearLayout.LayoutParams).apply {
                        setMargins(Tools.dp2px(context, 30f), Tools.dp2px(context, 10f), Tools.dp2px(context, 30f), Tools.dp2px(context, 10f))
                    }
                    setBackgroundStyle(HyperEditBackgroundStyle.STROKE)
                    isSingleLine = true
                    hint = "请输入"
                }
            }
            custom(HyperEditText::class.java) {
                (it as HyperEditText).apply {
                    (layoutParams as LinearLayout.LayoutParams).apply {
                        setMargins(Tools.dp2px(context, 30f), Tools.dp2px(context, 10f), Tools.dp2px(context, 30f), Tools.dp2px(context, 10f))
                    }
                    setBackgroundStyle(HyperEditBackgroundStyle.NORMAL)
                    isSingleLine = true
                    hint = "请输入"
                }
            }
        }
    }
}