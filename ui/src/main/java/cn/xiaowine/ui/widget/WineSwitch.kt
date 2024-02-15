package cn.xiaowine.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import cn.xiaowine.ui.appcompat.HyperSwitch

class WineSwitch(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseWineText(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    val switchView = HyperSwitch(context).apply {
        gravity = Gravity.CENTER_VERTICAL
        id = View.generateViewId()
    }

    var isChecked: Boolean
        get() {
            return switchView.isChecked
        }
        set(value) {
            switchView.isChecked = value
        }

    fun onClick(onClick: ((Boolean) -> Unit)?) {
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


    init {
        ConstraintSet().apply {
            clone(constraintLayout)
            connect(switchView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
            connect(switchView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            connect(switchView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            applyTo(constraintLayout)
        }
        addCustomizeView(switchView)
    }
}