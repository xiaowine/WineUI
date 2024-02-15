package cn.xiaowine.ui.appcompat

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.tools.Tools.dp
import java.lang.reflect.Field

class HyperSwitch(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : SwitchCompat(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, androidx.appcompat.R.attr.switchStyle)
    constructor(context: Context) : this(context, null)


    init {
        background = null
        val drawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 25f * resources.displayMetrics.density
            setSize(32.dp, 32.dp)
            setColor(Color.WHITE)
            setStroke(10.dp, Color.TRANSPARENT)
        }
        setThumbDrawable(drawable)
        val switchOnDrawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 25.dp.toFloat()
            setSize(26.dp, 22.dp)
            setColor(ContextCompat.getColor(context, R.color.switch_on_color))
        }

        val switchOffDrawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 25f.dp
            setSize(26.dp, 22.dp)
            setColor(ContextCompat.getColor(context, R.color.color_track_color))
        }

        val stateListDrawable = StateListDrawable().apply {
            addState(intArrayOf(android.R.attr.state_checked), switchOnDrawable)
            addState(intArrayOf(-android.R.attr.state_checked), switchOffDrawable)
        }

        trackDrawable = stateListDrawable
        showText = false
    }

    @SuppressLint("DiscouragedPrivateApi", "DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val mSwitchWidth: Field = SwitchCompat::class.java.getDeclaredField("mSwitchWidth")
        mSwitchWidth.isAccessible = true
        mSwitchWidth.setInt(this, 56.dp)
    }

}