package cn.xiaowine.app

import android.widget.Toast
import cn.xiaowine.ui.page.WinePage

class APage : WinePage() {

    init {
        initPage {
            title {
                text = this@APage::class.java.simpleName
            }
            title {
                text = "标题"
            }
            text {
                title = "这是一个标题"
                onClick {
                    toPage(BPage::class.java)
                }
            }
            text {
                setIcon(R.drawable.ic_launcher_background)
                title = "这是一个带图标的标题"
                onClick{
                    backPage()
                }
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
            line()
            title {
                text = "开关"
            }
            switch {
                title = "开关"
                isChecked = true
                onClick {
                    Toast.makeText(context, "onClick value:$it", Toast.LENGTH_SHORT).show()
                }
                onLongClick {
                    Toast.makeText(context, "onLongClick value:$it", Toast.LENGTH_SHORT).show()
                }
            }
            switch {
                title = "开关"
                onClick {
                    Toast.makeText(context, "onClick value:$it", Toast.LENGTH_SHORT).show()
                }
                onLongClick {
                    Toast.makeText(context, "onLongClick value:$it", Toast.LENGTH_SHORT).show()
                }
                setIcon(R.drawable.ic_launcher_background)
            }
            switch {
                title = "带摘要的开关"
                summary = "这是一个摘要"
                isChecked = true
                onClick {
                    Toast.makeText(context, "onClick value:$it", Toast.LENGTH_SHORT).show()
                }
                onLongClick {
                    Toast.makeText(context, "onLongClick value:$it", Toast.LENGTH_SHORT).show()
                }
            }
            switch {
                title = "带摘要带图标的开关"
                summary = "这是一个摘要"
                isChecked = false
                onClick {
                    Toast.makeText(context, "onClick value:$it", Toast.LENGTH_SHORT).show()
                }
                onLongClick {
                    Toast.makeText(context, "onLongClick value:$it", Toast.LENGTH_SHORT).show()
                }
                setIcon(R.drawable.ic_launcher_background)
            }
            line()
        }
    }
}