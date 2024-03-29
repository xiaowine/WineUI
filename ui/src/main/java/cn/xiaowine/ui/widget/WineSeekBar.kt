package cn.xiaowine.ui.widget

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import cn.xiaowine.ui.appcompat.HyperSeekBar
import cn.xiaowine.ui.databinding.WineSeekBinding
import cn.xiaowine.ui.tools.Tools.dp
import cn.xiaowine.ui.tools.Tools.hideView
import cn.xiaowine.ui.tools.Tools.showView
import kotlin.math.roundToInt
import kotlin.properties.Delegates


class WineSeekBar(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private var onProgressChangedListener: ProgressChangedListener? = null
    private var _binding: WineSeekBinding? = null
    private val binding: WineSeekBinding get() = _binding!!

    val minText: AppCompatTextView get() = binding.minText
    val maxText: AppCompatTextView get() = binding.maxText
    val nowText: AppCompatTextView get() = binding.nowText
    val seekBar: HyperSeekBar get() = binding.seekBar
    val textLayout: LinearLayout get() = binding.textLayout
    private val constraintLayout: ConstraintLayout get() = binding.constraintLayout
    private val fragment: FrameLayout get() = binding.fragment


    //    Android 8.1以上才支持设置最小值
    var minProgress by Delegates.observable(0) { _, _, newValue ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (newValue < -9999) {
                error("The minimum value cannot be less than -9999")
            }
            minText.text = newValue.toString()
            seekBar.min = newValue
        }
    }

    //    最大值9999
    var maxProgress by Delegates.observable(0) { _, _, newValue ->
        if (newValue > 9999) {
            error("The maximum value cannot exceed 9999")
        }
        maxText.text = newValue.toString()
        seekBar.max = newValue
    }

    var nowProgress by Delegates.observable(0) { _, _, newValue ->
        post {
            nowText.text = newValue.toString()
            seekBar.progress = newValue
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (nowProgress !in minProgress..maxProgress) {
            error("The current value cannot exceed the maximum value or be less than the minimum value")
        }
    }

    private fun AppCompatTextView.createTextView(text: Int): AppCompatTextView {
        return this.apply {
            this.text = text.toString()
            if (layoutParams is LinearLayout.LayoutParams) {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                setPadding(20.dp, 0, 20.dp, 0)
                layoutParams = LinearLayout.LayoutParams(0, 31.dp, 1f)
            } else {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                setPadding(10.dp, 0, 0, 0)
                layoutParams.apply {
                    width = (55 * resources.configuration.fontScale + 5).dp.roundToInt()
                }
                gravity = Gravity.CENTER_VERTICAL or Gravity.END
                typeface = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    Typeface.create(Typeface.DEFAULT, 600, false)
                } else {
                    Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                }
            }
        }
    }


    init {
        _binding = WineSeekBinding.inflate(LayoutInflater.from(context), this, true)
        ConstraintSet().apply {
            clone(constraintLayout)
            connect(fragment.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 15.dp)
            connect(fragment.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 15.dp)
            applyTo(constraintLayout)
        }
        minText.createTextView(minProgress)
        maxText.createTextView(maxProgress)
        nowText.createTextView(nowProgress)
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
        seekBar.setLongClickListener(object : HyperSeekBar.LongClickListener {
            override fun onLongClick(seekBar: SeekBar, progress: Int) {
                onLongClick?.invoke(seekBar, progress)
            }
        })
        nowText.setOnLongClickListener {
            onLongClick?.invoke(seekBar, seekBar.progress)
            true
        }
    }

    interface ProgressChangedListener {
        fun onChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean)
    }

}