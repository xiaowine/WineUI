package cn.xiaowine.app.pages

import android.annotation.SuppressLint
import android.widget.LinearLayout
import android.widget.Toast
import cn.xiaowine.ui.WinePage
import cn.xiaowine.ui.tools.Tools.dp

@SuppressLint("SetTextI18n")
class ButtonPage : WinePage() {

    init {
        initPage {
            toPageText()
            title {
                text = "Button"
            }
            button {
                text = "Click me"
                setOnClickListener {
                    Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
                }
                (layoutParams as LinearLayout.LayoutParams).apply {
                    setMargins(30.dp, 10.dp, 30.dp, 10.dp)
                }
            }
            button {
                text = "Click me"
                setOnClickListener {
                    Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
                }
                (layoutParams as LinearLayout.LayoutParams).apply {
                    setMargins(30.dp, 10.dp, 30.dp, 10.dp)
                }
                isEnabled = false
            }
            button {
                text = "Click me"
                setOnClickListener {
                    Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
                }
                (layoutParams as LinearLayout.LayoutParams).apply {
                    setMargins(30.dp, 10.dp, 30.dp, 10.dp)
                }
                setCancel(true)
            }
        }
    }
}