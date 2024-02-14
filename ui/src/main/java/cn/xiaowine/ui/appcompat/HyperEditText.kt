package cn.xiaowine.ui.appcompat

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools.dp2px

class HyperEditText(context: Context) : AppCompatEditText(context) {
    init {
        minHeight = dp2px(context, 50f)
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            setMargins(dp2px(context, 30f), 0, dp2px(context, 30f), 0)
        }
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        setTextColor(context.getColor(R.color.title_text_color))
        gravity =  Gravity.CENTER_VERTICAL
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = dp2px(context, 15f).toFloat()
            setStroke(dp2px(context, 2f), ContextCompat.getColor(context, R.color.button_default_color))
            setColor(ContextCompat.getColor(context, R.color.dialog_edit_background_color))
        }
        setHintTextColor(context.getColor(R.color.edittext_hit_color))
    }
}