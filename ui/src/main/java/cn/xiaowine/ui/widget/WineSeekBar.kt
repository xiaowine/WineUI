package cn.xiaowine.ui.widget

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools.dp2px
import cn.xiaowine.ui.Tools.hideView
import cn.xiaowine.ui.Tools.showView
import cn.xiaowine.ui.appcompat.HyperSeekBar


class WineSeekBar(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private var onProgressChangedListener: ProgressChangedListener? = null

    val inflate: ConstraintLayout
    var minProgress: Int
        get() {
            return minText.text.toString().toInt()
        }
        set(value) {
            minText.text = value.toString()
            seekBar.min = value
        }
    var maxProgress: Int
        get() {
            return maxText.text.toString().toInt()
        }
        set(value) {
            maxText.text = value.toString()
            seekBar.max = value
        }
    var nowProgress: Int
        get() {
            return nowText.text.toString().toInt()
        }
        set(value) {
            nowText.text = value.toString()
            seekBar.progress = value
        }

    private fun TextView.createTextView(stringRes: Int): TextView {
        return this.apply {
            text = context.getString(stringRes)
            if (layoutParams is LinearLayout.LayoutParams) {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                setPadding(dp2px(context, 20f), 0, dp2px(context, 20f), 0)
                layoutParams = LinearLayout.LayoutParams(0, dp2px(context, 31f), 1f)
            } else {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                setPadding(dp2px(context, 12f), 0, 0, 0)
                layoutParams.apply {
                    width = dp2px(context, 50f)
                }
            }
            typeface = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Typeface.create(Typeface.DEFAULT, 600, false)
            } else {
                Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            }
        }
    }

    val minText: TextView
        get() {
            return inflate.findViewById(R.id.minText)
        }
    val maxText: TextView
        get() {
            return inflate.findViewById(R.id.maxText)
        }
    val nowText: TextView
        get() {
            return inflate.findViewById(R.id.nowText)
        }
    val seekBar: HyperSeekBar
        get() {
            return inflate.findViewById(R.id.seekBar)
        }
    val textLayout: LinearLayout
        get() {
            return inflate.findViewById(R.id.textLayout)
        }

    init {
        inflate = inflate(context, R.layout.wine_seek, this) as ConstraintLayout
        val constraintLayout = inflate.findViewById<ConstraintLayout>(R.id.constraintLayout)
        ConstraintSet().apply {
            clone(constraintLayout)
            connect(R.id.fragment, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, dp2px(context, 15f))
            connect(R.id.fragment, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, dp2px(context, 15f))
            applyTo(constraintLayout)
        }
        minText.createTextView(R.string.WineSeekBar_MinProgress)
        maxText.createTextView(R.string.WineSeekBar_MaxProgress)
        nowText.createTextView(R.string.WineSeekBar_NowProgress)
        seekBar.apply {
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    nowText.text = progress.toString()
                    onProgressChangedListener?.onChanged(seekBar, progress, fromUser)
                }


                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    textLayout.showView()
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    textLayout.hideView()
                }
            })
            progress = this@WineSeekBar.nowProgress
        }
    }

    fun onProgressChanged(onProgressChangedListener: ProgressChangedListener?) {
        this.onProgressChangedListener = onProgressChangedListener
    }

    fun onLongClick(onLongClick: ((seekBar: SeekBar, progress: Int) -> Unit)? = null) {
        if (onLongClick == null) {
            seekBar.setOnLongClickListener(null)
        } else {
            seekBar.setLongClickListener(object : HyperSeekBar.LongClickListener {
                override fun onLongClick(seekBar: SeekBar, progress: Int) {
                    onLongClick.invoke(seekBar, progress)
                }
            })
        }
    }

    interface ProgressChangedListener {
        fun onChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean)
    }

}