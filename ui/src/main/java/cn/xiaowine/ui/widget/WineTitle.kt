package cn.xiaowine.ui.widget

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools.dp2px

class WineTitle(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : AppCompatTextView(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        setPadding(0, dp2px(context, 9.75f), 0, dp2px(context, 9.75f))
        setTextColor(ContextCompat.getColor(context, R.color.title_color))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            paint.typeface = Typeface.create(null, 400, false)
        } else {
            paint.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        }
    }
}