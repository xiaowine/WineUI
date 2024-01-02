package cn.xiaowine.ui.widget


import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout
import android.widget.TextView
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools.dp2px


@SuppressLint("AppCompatCustomView")
class WineCardLink(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : TextView(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    var title: String
        get() {
            return text.toString()
        }
        set(value) {
            text = value
        }

    init {
        layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT).apply {
            setMargins(0, dp2px(context, 10f), 0, dp2px(context, 10f))
        }
        setTextColor(context.getColor(R.color.card_link_color))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.5f)
    }
}