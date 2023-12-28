package cn.xiaowine.app

import cn.xiaowine.ui.annotation.Coroutine
import cn.xiaowine.ui.page.WinePage

class CPage : WinePage() {

    init {
        initPage {
            title {
                text = this@CPage::class.java.simpleName
            }
            title {
                text = "标题"
            }
            text {
                title = "backPage"
                onClick {
                    backPage()
                }
            }
            text {
                setIcon(R.drawable.ic_launcher_background)
                title = "这是一个带图标的标题"
            }
        }
    }
}