package cn.xiaowine.ui.appcompat

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.data.HyperEditBackgroundStyle
import cn.xiaowine.ui.tools.HyperEditTextFocusTools.hideKeyboardAndClearFocus
import cn.xiaowine.ui.tools.Tools.dp2px


class HyperEditText(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : AppCompatEditText(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, androidx.appcompat.R.attr.editTextStyle)
    constructor(context: Context) : this(context, null)

   private var isCustomBackgroundStyle: Boolean = false

    init {
        minHeight = dp2px(context, 50f)
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            setMargins(dp2px(context, 30f), 0, dp2px(context, 30f), 0)
        }
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        setTextColor(ContextCompat.getColor(context, R.color.title_text_color))
        gravity = Gravity.CENTER_VERTICAL
        background = createBackgroundDrawable(false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val drawable = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                setSize(dp2px(context, 2f), dp2px(context, 2f))
                setColor(ContextCompat.getColor(context, R.color.edittext_background_stroke))
            }
            setTextCursorDrawable(drawable)
        }
        imeOptions = EditorInfo.IME_ACTION_DONE
        setPadding(dp2px(context, 20f), dp2px(context, 15f), dp2px(context, 20f), dp2px(context, 15f))
        setHintTextColor(ContextCompat.getColor(context, R.color.edittext_hit_color))
        setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                hideKeyboardAndClearFocus(context, this)
            }
            if (!isCustomBackgroundStyle) {
                background = createBackgroundDrawable(hasFocus)
            }
        }
    }

    fun setBackgroundStyle(backgroundStyle: HyperEditBackgroundStyle?) {
        isCustomBackgroundStyle = backgroundStyle != null
        background = when (backgroundStyle) {
            HyperEditBackgroundStyle.NORMAL -> {
                createBackgroundDrawable(false)
            }

            HyperEditBackgroundStyle.STROKE -> {
                createBackgroundDrawable(true)
            }

            null -> {
                createBackgroundDrawable(false)
            }
        }

    }

    private fun createBackgroundDrawable(hasFocus: Boolean): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = dp2px(context, 15f).toFloat()
            if (hasFocus) {
                setStroke(dp2px(context, 2f), ContextCompat.getColor(context, R.color.edittext_background_stroke))
            }
            setColor(ContextCompat.getColor(context, R.color.dialog_edit_background_color))
        }
    }


    override fun setSingleLine(singleLine: Boolean) {
        super.setSingleLine(singleLine)
        (layoutParams as LinearLayout.LayoutParams).apply {
            height = if (singleLine) {
                dp2px(context, 50f)
            } else {
                LinearLayout.LayoutParams.WRAP_CONTENT
            }
        }
    }
}