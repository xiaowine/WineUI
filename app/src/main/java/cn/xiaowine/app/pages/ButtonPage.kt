package cn.xiaowine.app.pages

import android.annotation.SuppressLint
import android.widget.LinearLayout
import android.widget.Toast
import cn.xiaowine.ui.WinePage
import cn.xiaowine.ui.tools.Tools

@SuppressLint("SetTextI18n")
class ButtonPage : WinePage() {

    init {
        initPage {
            toPageText()
            title {
                text = "SeekBar"
            }
            button {
                text = "Click me"
                setOnClickListener {
                    Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
                }
                (layoutParams as LinearLayout.LayoutParams).apply {
                    setMargins(Tools.dp2px(context, 30f), Tools.dp2px(context, 10f), Tools.dp2px(context, 30f), Tools.dp2px(context, 10f))
                }
            }
            button {
                text = "Click me"
                setOnClickListener {
                    Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
                }
                (layoutParams as LinearLayout.LayoutParams).apply {
                    setMargins(Tools.dp2px(context, 30f), Tools.dp2px(context, 10f), Tools.dp2px(context, 30f), Tools.dp2px(context, 10f))
                }
                isEnabled = false
            }
            button {
                text = "Click me"
                setOnClickListener {
                    Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
                }
                (layoutParams as LinearLayout.LayoutParams).apply {
                    setMargins(Tools.dp2px(context, 30f), Tools.dp2px(context, 10f), Tools.dp2px(context, 30f), Tools.dp2px(context, 10f))
                }
                setCancel(true)
            }
        }
    }
}