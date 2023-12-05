package cn.xiaowine.app

import android.widget.Toast
import cn.xiaowine.ui.page.WinePage

class BPage : WinePage() {

    init {
        initPage {
            title {
                text = "标题"
            }
            text {
                title = "这是一个标题"
            }
            text {
                setIcon(R.drawable.ic_launcher_background)
                title = "这是一个带图标的标题"
            }
        }
    }
}