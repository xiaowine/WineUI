package cn.xiaowine.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet
import cn.xiaowine.ui.R

class WineText(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseWineText(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)


    val arrowImage = ImageView(context).apply {
        id = View.generateViewId()
        setImageResource(R.drawable.ic_right_arrow)
        setImageResource(R.drawable.ic_right_arrow)
        visibility = View.GONE
    }

    override fun onClick(onClick: (() -> Unit)?) {
        haveArrow(onClick != null)
        binding.root.setOnClickListener {
            onClick?.invoke()
        }
    }

    fun haveArrow(boolean: Boolean = true) {
        arrowImage.visibility = if (boolean) VISIBLE else GONE
    }

    init {
        ConstraintSet().apply {
            clone(constraintLayout)
            connect(arrowImage.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
            applyTo(constraintLayout)
        }
        addCustomizeView(arrowImage)
    }
}