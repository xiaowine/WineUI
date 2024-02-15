package cn.xiaowine.ui.appcompat

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.data.HyperEditBackgroundStyle
import cn.xiaowine.ui.tools.HyperEditTextFocusTools.hideKeyboardAndClearFocus
import cn.xiaowine.ui.tools.Tools.dp

class HyperEditText(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : AppCompatEditText(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, androidx.appcompat.R.attr.editTextStyle)
    constructor(context: Context) : this(context, null)

    private var isCustomBackgroundStyle: Boolean = false

    private var animator: ValueAnimator? = null

    private val mHeight = 55.dp

    init {
        minHeight = mHeight
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            setMargins(30.dp, 0, 30.dp, 0)
        }
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        setTextColor(ContextCompat.getColor(context, R.color.title_text_color))
        gravity = Gravity.CENTER_VERTICAL
        background = createBackgroundDrawable(false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val drawable = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                setSize(2.dp, 2.dp)
                setColor(ContextCompat.getColor(context, R.color.edittext_background_stroke))
            }
            setTextCursorDrawable(drawable)
        }
        imeOptions = EditorInfo.IME_ACTION_DONE
        setPadding(20.dp, 15.dp, 20.dp, 15.dp)
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
            cornerRadius = 15f.dp
            if (hasFocus) {
                animateStrokeWidth(0.dp, 2.dp)
            }
            setColor(ContextCompat.getColor(context, R.color.dialog_edit_background_color))
        }
    }

    private fun animateStrokeWidth(from: Int, to: Int) {
        animator = ValueAnimator.ofInt(from, to)
        animator?.duration = 300
        animator?.addUpdateListener { animation ->
            val strokeWidth = animation.animatedValue as Int
            (background as? GradientDrawable)?.setStroke(strokeWidth, ContextCompat.getColor(context, R.color.edittext_background_stroke))
        }
        animator?.start()
    }

    fun cancelAnimation() {
        animator?.cancel()
    }

    override fun setSingleLine(singleLine: Boolean) {
        super.setSingleLine(singleLine)
        (layoutParams as LinearLayout.LayoutParams).apply {
            height = if (singleLine) {
                mHeight
            } else {
                LinearLayout.LayoutParams.WRAP_CONTENT
            }
        }
    }
}