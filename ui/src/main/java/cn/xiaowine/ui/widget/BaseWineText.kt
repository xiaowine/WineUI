package cn.xiaowine.ui.widget

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.databinding.BaseTextBinding
import cn.xiaowine.ui.tools.Tools.dp

open class BaseWineText(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private var _binding: BaseTextBinding? = null
    val binding: BaseTextBinding get() = _binding!!


    val titleView: AppCompatTextView get() = binding.titleView
    val summaryView: AppCompatTextView get() = binding.summaryView
    val customizeAreaView: LinearLayout get() = binding.customizeAreaView
    val constraintLayout: ConstraintLayout get() = binding.constraintLayout
    val linearLayout: LinearLayout get() = binding.linearLayout
    val iconView: ImageView get() = binding.imageView

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


    open fun onClick(onClick: ((BaseWineText) -> Unit)? = null) {
        setOnClickListener {
            onClick?.invoke(it as BaseWineText)
        }
    }

    fun onLongClick(onLongClick: ((BaseWineText) -> Unit)? = null) {
        setOnLongClickListener {
            onLongClick?.invoke(it as BaseWineText)
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

    fun addCustomizeView(customizeView: View) {
        customizeAreaView.addView(customizeView)
    }

    init {
        _binding = BaseTextBinding.inflate(LayoutInflater.from(context), this, true)
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
        linearLayout.setPadding(0, 20.dp, 0, 20.dp)
        ConstraintSet().apply {
            clone(constraintLayout)
            connect(linearLayout.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 20.dp)
            connect(iconView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            connect(iconView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            connect(linearLayout.id, ConstraintSet.START, iconView.id, ConstraintSet.END, 0)
            applyTo(constraintLayout)
        }
    }
}