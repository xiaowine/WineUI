package cn.xiaowine.ui.widget


import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools.dp2px

class WineLine(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)


    init {
        this.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp2px(context, 0.9f)).apply {
            setMargins( dp2px(context, 28f), dp2px(context, 15f),  dp2px(context, 28f), dp2px(context, 15f))
        }
        setBackgroundColor(context.getColor(R.color.line_color))
    }
}