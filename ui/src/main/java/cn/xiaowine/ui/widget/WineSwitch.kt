package cn.xiaowine.ui.widget

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.appcompat.HyperSwitch
import cn.xiaowine.ui.databinding.WineSwitchBinding
import cn.xiaowine.ui.tools.Tools.dp

class WineSwitch(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private var _binding: WineSwitchBinding? = null

    private val binding: WineSwitchBinding get() = _binding!!

    val titleView: AppCompatTextView get() = binding.titleView
    val summaryView: AppCompatTextView get() = binding.summaryView
    val switchView: HyperSwitch get() = binding.switchView
    val iconView: ImageView get() = binding.imageView
    private val constraintLayout: ConstraintLayout get() = binding.constraintLayout
    val linearLayout: LinearLayout get() = binding.linearLayout
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
        switchView.setOnCheckedChangeListener { v, b ->
            if (v.isPressed) {
                onClick?.invoke(b)
            }
        }
    }

    fun onLongClick(onLongClick: ((Boolean) -> Unit)? = null) {
        binding.root.setOnLongClickListener {
            onLongClick?.invoke(switchView.isClickable)
            true
        }
    }


    fun setIcon(resId: Int) {
        setIcon(ContextCompat.getDrawable(context, resId))
    }

    fun setIcon(drawable: Drawable?) {
        if (drawable != null) {
            ConstraintSet().apply {
                clone(constraintLayout)
                connect(linearLayout.id, ConstraintSet.START, iconView.id, ConstraintSet.END, 60.dp)
                applyTo(constraintLayout)
            }
        }
        iconView.visibility = if (drawable == null) GONE else VISIBLE
        iconView.setImageDrawable(drawable)
    }


    init {
        _binding = WineSwitchBinding.inflate(LayoutInflater.from(context), this, true)
        constraintLayout.setOnClickListener { switchView.performClick() }
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
            layoutParams = LayoutParams(45.dp, 45.dp)
        }
        switchView.apply {
            gravity = Gravity.CENTER_VERTICAL
        }
        linearLayout.setPadding(0, 20.dp, 0, 20.dp)
        ConstraintSet().apply {
            clone(constraintLayout)
            connect(switchView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
            connect(switchView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            connect(switchView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            connect(iconView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            connect(iconView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            connect(linearLayout.id, ConstraintSet.START, iconView.id, ConstraintSet.END, 0)
            applyTo(constraintLayout)
        }
    }
}