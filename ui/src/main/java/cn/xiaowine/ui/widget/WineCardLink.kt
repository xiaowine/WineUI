package cn.xiaowine.ui.widget

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.tools.Tools.dp

class WineCardLink(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : AppCompatTextView(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, android.R.attr.textViewStyle)
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
            setMargins(0, 10.dp, 0, 10.dp)
        }
        setTextColor(ContextCompat.getColor(context, R.color.card_link_color))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.5f)
        typeface = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Typeface.create(null, 500, false)
        } else {
            Typeface.defaultFromStyle(Typeface.BOLD)
        }
    }

    fun onClick(onClick: ((WineCardLink) -> Unit)? = null) {
        setOnClickListener {
            onClick?.invoke(it as WineCardLink)
        }
    }

    fun onLongClick(onLongClick: ((WineCardLink) -> Unit)? = null) {
        setOnLongClickListener {
            onLongClick?.invoke(it as WineCardLink)
            true
        }
    }
}