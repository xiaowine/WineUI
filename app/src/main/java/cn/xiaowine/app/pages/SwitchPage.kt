package cn.xiaowine.app.pages

import android.widget.Toast
import cn.xiaowine.app.R
import cn.xiaowine.ui.WinePage

class SwitchPage : WinePage() {

    init {
        initPage {
            toPageText()
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
        }
    }
}