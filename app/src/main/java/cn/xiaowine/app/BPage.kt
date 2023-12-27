package cn.xiaowine.app

import cn.xiaowine.ui.annotation.Coroutine
import cn.xiaowine.ui.page.WinePage
@Coroutine
class BPage : WinePage() {

    init {
        initPage {
            title {
                text = this@BPage::class.java.simpleName
            }
            title {
                text = "标题"
            }
            text {
                title = "这是一个标题"
                onClick {
                    backPage()
                }
            }
            for (i in 0..100) {
                text {
                    setIcon(R.drawable.ic_launcher_background)
                    title = "这是一个带图标的标题"
                }
            }
        }
    }
}