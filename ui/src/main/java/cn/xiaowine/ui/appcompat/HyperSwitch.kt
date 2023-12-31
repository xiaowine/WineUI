package cn.xiaowine.ui.appcompat

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SwitchCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools.dp2px
import java.lang.reflect.Field

class HyperSwitch(context: Context, attrs: AttributeSet) : SwitchCompat(context, attrs) {

    init {
        background = null
        setThumbResource(R.drawable.switch_thumb)
        setTrackResource(R.drawable.switch_track)
        showText = false
    }

    @SuppressLint("DiscouragedPrivateApi", "DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val mSwitchWidth: Field = SwitchCompat::class.java.getDeclaredField("mSwitchWidth")
        mSwitchWidth.isAccessible = true
        mSwitchWidth.setInt(this, dp2px(context, 56f))
    }

}