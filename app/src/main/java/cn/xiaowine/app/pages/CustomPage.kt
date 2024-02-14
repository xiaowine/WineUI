package cn.xiaowine.app.pages

import android.annotation.SuppressLint
import cn.xiaowine.ui.appcompat.HyperEditText
import cn.xiaowine.ui.page.WinePage

@SuppressLint("SetTextI18n")
class CustomPage : WinePage() {

    init {
        initPage {
            toPageText()
            title { text = "Custom" }
            custom(HyperEditText::class.java) {
                (it as HyperEditText).apply {
                    hint = "请输入"
                }
            }
        }
    }
}