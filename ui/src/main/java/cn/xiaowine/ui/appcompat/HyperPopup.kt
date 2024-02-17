package cn.xiaowine.ui.appcompat

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.ListPopupWindow
import cn.xiaowine.ui.R
import cn.xiaowine.ui.tools.Tools.dp

class HyperPopup(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ListPopupWindow(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, androidx.appcompat.R.attr.listPopupWindowStyle)
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
        animationStyle = R.style.Theme_WinePopupAnim

    }

    override fun dismiss() {
        super.dismiss()
    }

    override fun show() {
        horizontalOffset = (-24).dp
        setDropDownGravity(Gravity.RIGHT)
        super.show()
        listView?.apply {
            isScrollbarFadingEnabled = false
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false
        }
    }
}