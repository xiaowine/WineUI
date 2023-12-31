package cn.xiaowine.ui.appcompat

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools.createRoundShape
import cn.xiaowine.ui.Tools.dp2px


class HyperButton(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : AppCompatButton(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private var isCancel: Boolean = false

    init {
        gravity = Gravity.CENTER
        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        setTextColor(ContextCompat.getColor(context, R.color.button_text_color))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 17.5f)
        stateListAnimator = null
        setDefaultBackground()
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp2px(context, 51f), 1f)
    }

    private fun setDefaultBackground() {
        val defaultDrawable = createRoundShape(dp2px(context, 18f).toFloat(), ContextCompat.getColor(context, R.color.button_default_color))
        val pressedDrawable = createRoundShape(dp2px(context, 18f).toFloat(), ContextCompat.getColor(context, R.color.button_pressed_color))
        val disabledDrawable = createRoundShape(dp2px(context, 18f).toFloat(), ContextCompat.getColor(context, R.color.button_disable_color))
        background = StateListDrawable().apply {
            addState(intArrayOf(-android.R.attr.state_enabled), disabledDrawable)
            addState(intArrayOf(android.R.attr.state_pressed), pressedDrawable)
            addState(intArrayOf(), defaultDrawable)
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        if (enabled) {
            setTextColor(ContextCompat.getColor(context, R.color.button_text_color))
        } else {
            setTextColor(ContextCompat.getColor(context, R.color.button_disable_text_color))
        }
    }

    fun setCancel(cancel: Boolean) {
        isCancel = cancel
        if (cancel) {
            setTextColor(ContextCompat.getColor(context, R.color.button_cancel_text_color))
            background = StateListDrawable().apply {
                addState(intArrayOf(-android.R.attr.state_enabled), createRoundShape(dp2px(context, 18f).toFloat(), ContextCompat.getColor(context, R.color.button_cancel_disable_color)))
                addState(intArrayOf(android.R.attr.state_pressed), createRoundShape(dp2px(context, 18f).toFloat(), ContextCompat.getColor(context, R.color.button_cancel_pressed_color)))
                addState(intArrayOf(), createRoundShape(dp2px(context, 18f).toFloat(), ContextCompat.getColor(context, R.color.button_cancel_color)))
            }
        } else {
            setTextColor(ContextCompat.getColor(context, R.color.button_text_color))
            setDefaultBackground()
        }
    }

    fun getCancel(): Boolean {
        return isCancel
    }
}