package cn.xiaowine.app

import android.widget.Toast
import cn.xiaowine.ui.page.WinePage

class APage : WinePage() {

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
            text {
                title = "这是一个摘要"
                summary = "这是一个带摘要的标题"
            }
            text {
                title = "这是一个摘要"
                summary = "这是一个带图标带摘要的标题"
                setIcon(R.drawable.ic_launcher_background)
            }
            text {
                title = "Title with Summary"
                summary = "This is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summary"
            }
            text {
                title = "Title with Summary"
                summary = "This is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summaryThis is a summary"
                setIcon(R.drawable.ic_launcher_background)
            }
            line()
            title {
                text = "Switch"
            }
            switch {
                title = "Switch"
                isChecked = true
                onClick {
                    Toast.makeText(context, "onClick value:$it", Toast.LENGTH_SHORT).show()
                }
                onLongClick {
                    Toast.makeText(context, "onLongClick value:$it", Toast.LENGTH_SHORT).show()
                }
            }
            switch {
                title = "Switch"
                isChecked = true
                onClick {
                    Toast.makeText(context, "onClick value:$it", Toast.LENGTH_SHORT).show()
                }
                onLongClick {
                    Toast.makeText(context, "onLongClick value:$it", Toast.LENGTH_SHORT).show()
                }
                setIcon(R.drawable.ic_launcher_background)
            }
            switch {
                title = "Switch with Summary"
                summary = "Summary"
                isChecked = false
                onClick {
                    Toast.makeText(context, "onClick value:$it", Toast.LENGTH_SHORT).show()
                }
                onLongClick {
                    Toast.makeText(context, "onLongClick value:$it", Toast.LENGTH_SHORT).show()
                }
            }
            switch {
                title = "Switch with Summary"
                summary = "Summary"
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