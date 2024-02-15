package cn.xiaowine.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.tools.Tools.dp

class WineCardTitle(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : AppCompatTextView(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, android.R.attr.textViewStyle)
    constructor(context: Context) : this(context, null)

    init {
        layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT).apply {
            setMargins(0, 10.dp, 0, 10.dp)
        }
        setTextColor(ContextCompat.getColor(context, R.color.card_title_color))
        setTextColor(ContextCompat.getColor(context, R.color.card_title_color))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
    }
}