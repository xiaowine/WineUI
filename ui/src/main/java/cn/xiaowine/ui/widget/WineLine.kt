package cn.xiaowine.ui.widget


import android.content.Context
import android.view.View
import android.widget.LinearLayout
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools.dp2px

class WineLine(context: Context) : View(context) {


    init {
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp2px(context, 0.9f)).apply {
            setMargins(0, dp2px(context, 15f), 0, dp2px(context, 15f))
        }
        this.layoutParams = layoutParams
        setBackgroundColor(context.getColor(R.color.line_color))
    }
}