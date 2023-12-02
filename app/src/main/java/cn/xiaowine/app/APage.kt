package cn.xiaowine.app

import android.widget.Toast
import cn.xiaowine.ui.page.WinePage

class APage : WinePage() {

    init {
        initPage {
            title {
                text = "Text"
            }
            text {
                title = "This is a title"
            }
            text {
                setIcon(R.drawable.ic_launcher_background)
                title = "This is a title"
            }
            text {
                title = "Title with Summary"
                summary = "This is a summary"
            }
            text {
                title = "Title with Summary"
                summary = "This is a summary"
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