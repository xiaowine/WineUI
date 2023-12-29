package cn.xiaowine.ui.widget


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools.dp2px


class WineText(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    val inflate: ConstraintLayout
    val titleView: TextView
        get() {
            return inflate.findViewById(R.id.title_view)
        }
    val summaryView: TextView
        get() {
            return inflate.findViewById(R.id.summary_view)
        }
    val arrowImage: ImageView
        get() {
            return inflate.findViewById(R.id.arrow_image)
        }
    val iconView: ImageView
        get() {
            return inflate.findViewById(R.id.imageView)
        }
    var title: String
        get() {
            return titleView.text.toString()
        }
        set(value) {
            titleView.text = value
        }
    var summary: String
        get() {
            return summaryView.text.toString()
        }
        set(value) {
            summaryView.text = value
        }


    fun onClick(onClick: (() -> Unit)? = null) {
        if (onClick == null) {
            inflate.setOnClickListener(null)
            haveArrow(false)
        } else {
            haveArrow(true)
            inflate.setOnClickListener {
                onClick.invoke()
            }
        }
    }

    fun onLongClick(onLongClick: (() -> Unit)? = null) {
        if (onLongClick == null) {
            inflate.setOnLongClickListener(null)
        } else {
            inflate.setOnLongClickListener {
                onLongClick.invoke()
                true
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setIcon(resId: Int) {
        setIcon(context.getDrawable(resId))
    }

    fun setIcon(drawable: Drawable?) {
        if (drawable == null) {
            iconView.visibility = GONE
        } else {
            val constraintLayout = inflate.findViewById<ConstraintLayout>(R.id.constraintLayout)
            ConstraintSet().apply {
                clone(constraintLayout)
                connect(R.id.linearLayout, ConstraintSet.START, R.id.imageView, ConstraintSet.END, dp2px(context, 60f))
                applyTo(constraintLayout)
            }
            iconView.visibility = VISIBLE
        }
        iconView.setImageDrawable(drawable)
    }

    fun haveArrow(boolean: Boolean = true) {
        arrowImage.visibility = if (boolean) VISIBLE else GONE
    }

    init {
        inflate = inflate(context, R.layout.wine_text, this) as ConstraintLayout
        titleView.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                paint.typeface = Typeface.create(null, 500, false)
            } else {
                paint.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            }
        }
        summaryView.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        }
        iconView.apply {
            layoutParams = LayoutParams(dp2px(context, 45f), dp2px(context, 45f))
        }
        arrowImage.apply {
            setImageResource(R.drawable.ic_right_arrow)
        }
        findViewById<LinearLayout>(R.id.linearLayout).apply {
            setPadding(0, dp2px(context, 20f), 0, dp2px(context, 20f))
        }
        val constraintLayout = inflate.findViewById<ConstraintLayout>(R.id.constraintLayout)
        ConstraintSet().apply {
            clone(constraintLayout)
//            connect(R.id.linearLayout, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
            connect(R.id.linearLayout, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, dp2px(context, 20f))
            connect(R.id.arrow_image, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
            connect(R.id.imageView, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            connect(R.id.imageView, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            connect(R.id.linearLayout, ConstraintSet.START, R.id.imageView, ConstraintSet.END, 0)
            applyTo(constraintLayout)
        }
    }
}