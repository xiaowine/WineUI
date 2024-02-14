package cn.xiaowine.ui.widget

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import cn.xiaowine.ui.Tools.dp2px
import cn.xiaowine.ui.Tools.hideView
import cn.xiaowine.ui.Tools.showView
import cn.xiaowine.ui.appcompat.HyperSeekBar
import cn.xiaowine.ui.databinding.WineSeekBinding
import kotlin.properties.Delegates


class WineSeekBar(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private var onProgressChangedListener: ProgressChangedListener? = null
    private var _binding: WineSeekBinding? = null
    private val binding: WineSeekBinding get() = _binding!!

    var minProgress by Delegates.observable(0) { _, _, newValue ->
        minText.text = newValue.toString()
        seekBar.min = newValue
    }
    var maxProgress by Delegates.observable(0) { _, _, newValue ->
        maxText.text = newValue.toString()
        seekBar.max = newValue
    }
    var nowProgress by Delegates.observable(0) { _, _, newValue ->
        post {
            nowText.text = newValue.toString()
        }
        post {
            seekBar.progress = newValue
        }
    }

    private fun AppCompatTextView.createTextView(text: Int): AppCompatTextView {
        return this.apply {
            this.text = text.toString()
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
                typeface = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    Typeface.create(Typeface.DEFAULT, 600, false)
                } else {
                    Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                }
            }
        }
    }

    val minText: AppCompatTextView get() = binding.minText
    val maxText: AppCompatTextView get() = binding.maxText
    val nowText: AppCompatTextView get() = binding.nowText
    val seekBar: HyperSeekBar get() = binding.seekBar
    val textLayout: LinearLayout get() = binding.textLayout
    private val constraintLayout: ConstraintLayout get() = binding.constraintLayout
    private val fragment: FrameLayout get() = binding.fragment


    init {
        _binding = WineSeekBinding.inflate(LayoutInflater.from(context), this, true)
        ConstraintSet().apply {
            clone(constraintLayout)
            connect(fragment.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, dp2px(context, 15f))
            connect(fragment.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, dp2px(context, 15f))
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
    }

    interface ProgressChangedListener {
        fun onChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean)
    }

}