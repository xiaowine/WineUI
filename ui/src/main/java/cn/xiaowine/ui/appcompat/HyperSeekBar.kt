package cn.xiaowine.ui.appcompat

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.tools.Tools.dp
import kotlin.math.abs

class HyperSeekBar(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : AppCompatSeekBar(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, androidx.appcompat.R.attr.seekBarStyle)
    constructor(context: Context) : this(context, null)

    private var downTime: Long = 0
    private var longClickListener: LongClickListener? = null
    private var lastY = 0f
    private var lastX = 0f
    private var hasLongTouch = false
    private var possibleLongTouch = true
    private val delayMillis = 400L

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1 && !hasLongTouch) {
                hasLongTouch = true
                longClickListener?.onLongClick(this@HyperSeekBar, progress)
            }
        }
    }


    init {
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val backgroundDrawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 50f
            orientation = GradientDrawable.Orientation.TOP_BOTTOM
            setColor(ContextCompat.getColor(context, R.color.seekbar_bg_color))
        }
        val progressDrawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 50f
            setColor(ContextCompat.getColor(context, R.color.seekbar_color))
        }
        val clipDrawable = ClipDrawable(progressDrawable, Gravity.START, ClipDrawable.HORIZONTAL)
        this.progressDrawable = LayerDrawable(arrayOf(backgroundDrawable, clipDrawable)).apply {
            setId(0, android.R.id.background)
            setId(1, android.R.id.progress)
        }
        indeterminateDrawable = ContextCompat.getDrawable(context, R.color.seekbar_color)
        thumb = null
        background = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            maxHeight = 31.dp
            minHeight = 31.dp
        } else {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 31.dp)
        }
        setPadding(0, 0, 0, 0)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val y = event.y
        val x = event.x
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                hasLongTouch = false
                possibleLongTouch = true
                lastY = y
                lastX = x
                downTime = System.currentTimeMillis()
                if (!handler.hasMessages(1)) {
                    handler.sendEmptyMessageDelayed(1, delayMillis)
                }
                return super.onTouchEvent(event)
            }

            MotionEvent.ACTION_MOVE -> {
                if (lastX != 0f && lastY != 0f && (abs(y - lastY) > 5 || abs(x - lastX) > 5)) {
                    possibleLongTouch = false
                    handler.removeMessages(1)
                    return super.onTouchEvent(event)
                }
                lastY = y
                lastX = x
            }

            MotionEvent.ACTION_UP -> {
                lastX = 0f
                lastY = 0f
                handler.removeMessages(1)
                if (System.currentTimeMillis() - downTime > delayMillis && possibleLongTouch) {
                    if (!hasLongTouch) {
                        hasLongTouch = true
                        longClickListener?.onLongClick(this, progress)
                    }
                    return true
                }
                return super.onTouchEvent(event)
            }

            MotionEvent.ACTION_CANCEL -> {
                handler.removeMessages(1)
                lastX = 0f
                lastY = 0f
                return super.onTouchEvent(event)
            }
        }
        return false
    }

    fun setLongClickListener(longClickListener: LongClickListener?) {
        this.longClickListener = longClickListener
    }

    interface LongClickListener {
        fun onLongClick(seekBar: SeekBar, progress: Int)
    }
}