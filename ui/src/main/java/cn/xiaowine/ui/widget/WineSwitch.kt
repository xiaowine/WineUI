package cn.xiaowine.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools.dp2px
import cn.xiaowine.ui.appcompat.HyperSwitch

class WineSwitch(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(context, attrs, defStyleAttr) {
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
    val switchView: HyperSwitch
        get() {
            return inflate.findViewById(R.id.switch_view)
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

    var isChecked: Boolean
        get() {
            return switchView.isChecked
        }
        set(value) {
            switchView.isChecked = value
        }

    fun onClick(onClick: ((Boolean) -> Unit)? = null) {
        if (onClick == null) {
            switchView.setOnCheckedChangeListener(null)
        } else {
            switchView.setOnCheckedChangeListener { _, b ->
                onClick.invoke(b)
            }
        }
    }

    fun onLongClick(onLongClick: ((Boolean) -> Unit)? = null) {
        if (onLongClick == null) {
            inflate.setOnLongClickListener(null)
        } else {
            inflate.setOnLongClickListener {
                onLongClick.invoke(switchView.isClickable)
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


    init {
        inflate = inflate(context, R.layout.wine_switch, this) as ConstraintLayout
//        inflate.minimumHeight = Tools.dp2px(context, 60f)
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
        switchView.apply {
            gravity = Gravity.CENTER_VERTICAL
        }
        findViewById<LinearLayout>(R.id.linearLayout).apply {
            setPadding(0, dp2px(context, 20f), 0, dp2px(context, 20f))
        }
        val constraintLayout = inflate.findViewById<ConstraintLayout>(R.id.constraintLayout)
        ConstraintSet().apply {
            clone(constraintLayout)
//            connect(R.id.linearLayout, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
            connect(R.id.switch_view, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
            connect(R.id.switch_view, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            connect(R.id.switch_view, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            connect(R.id.imageView, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            connect(R.id.imageView, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            connect(R.id.linearLayout, ConstraintSet.START, R.id.imageView, ConstraintSet.END, 0)
            applyTo(constraintLayout)
        }
//        setPadding(0, dp2px(context, 5f), 0, dp2px(context, 5f))
    }
}