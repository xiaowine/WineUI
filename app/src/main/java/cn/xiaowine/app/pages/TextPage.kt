package cn.xiaowine.app.pages

import android.annotation.SuppressLint
import cn.xiaowine.app.R
import cn.xiaowine.ui.annotation.Coroutine
import cn.xiaowine.ui.page.WinePage

@SuppressLint("SetTextI18n")
@Coroutine
class TextPage : WinePage() {

    init {
        initPage {
            toPageText()
            text {
                title = "这是一个标题"
            }
            text {
                setIcon(R.drawable.ic_launcher_background)
                title = "这是一个带图标的标题"
            }
            text {
                title = "这是一个带摘要的标题"
                summary = "这是一个摘要"
            }
            text {
                title = "这是一个带图标带摘要的标题"
                summary = "这是一个摘要"
                setIcon(R.drawable.ic_launcher_background)
            }
            text {
                title = "带摘要的标题"
                summary =
                    "这是摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘要"
            }
            text {
                title = "带摘要带图标的标题"
                summary =
                    "这是摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘要"
                setIcon(R.drawable.ic_launcher_background)
            }
        }
    }
}