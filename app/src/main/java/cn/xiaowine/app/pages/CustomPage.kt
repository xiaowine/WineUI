package cn.xiaowine.app.pages

import android.annotation.SuppressLint
import android.widget.LinearLayout
import cn.xiaowine.ui.WinePage
import cn.xiaowine.ui.appcompat.HyperEditText
import cn.xiaowine.ui.data.HyperEditBackgroundStyle
import cn.xiaowine.ui.tools.Tools.dp

@SuppressLint("SetTextI18n")
class CustomPage : WinePage() {

    init {
        initPage {
            toPageText()
            edittext {
                hint = "请输入"
                (layoutParams as LinearLayout.LayoutParams).apply {
                    setMargins(30.dp, 10.dp, 30.dp, 10.dp)
                }
            }
            custom(HyperEditText::class.java) {
                (it as HyperEditText).apply {
                    (layoutParams as LinearLayout.LayoutParams).apply {
                        setMargins(30.dp, 10.dp, 30.dp, 10.dp)
                    }
                    hint = "请输入"
                }
            }
            custom(HyperEditText::class.java) {
                (it as HyperEditText).apply {
                    (layoutParams as LinearLayout.LayoutParams).apply {
                        setMargins(30.dp, 10.dp, 30.dp, 10.dp)
                    }
                    isSingleLine = true
                    hint = "请输入"
                }
            }
            custom(HyperEditText::class.java) {
                (it as HyperEditText).apply {
                    (layoutParams as LinearLayout.LayoutParams).apply {
                        setMargins(30.dp, 10.dp, 30.dp, 10.dp)
                    }
                    setBackgroundStyle(HyperEditBackgroundStyle.NORMAL)
                    isSingleLine = true
                    hint = "请输入"
                }
            }
            custom(HyperEditText::class.java) {
                (it as HyperEditText).apply {
                    (layoutParams as LinearLayout.LayoutParams).apply {
                        setMargins(30.dp, 10.dp, 30.dp, 10.dp)
                    }
                    setBackgroundStyle(HyperEditBackgroundStyle.STROKE)
                    isSingleLine = true
                    hint = "请输入"
                }
            }
        }
    }
}