package cn.xiaowine.ui.appcompat

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.R
import androidx.appcompat.widget.ListPopupWindow
import cn.xiaowine.ui.tools.Tools.dp

class HyperPopup(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ListPopupWindow(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.attr.listPopupWindowStyle)
    constructor(context: Context) : this(context, null)

    init {
        val drawable = GradientDrawable().apply {
            setColor(Color.WHITE)
            cornerRadius = 20.dp.toFloat()
        }
        setBackgroundDrawable(drawable)
        width = 150.dp
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        isModal = true
    }
}