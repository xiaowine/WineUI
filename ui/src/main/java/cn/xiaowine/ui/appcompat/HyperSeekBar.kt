package cn.xiaowine.ui.appcompat

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools
import kotlin.math.abs


@SuppressLint("AppCompatCustomView")
class HyperSeekBar : SeekBar {
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

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        progressDrawable = ContextCompat.getDrawable(context, R.drawable.seekbar_progress)
        indeterminateDrawable = ContextCompat.getDrawable(context, R.color.seekbar_color)
        thumb = null
        background = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            maxHeight = Tools.dp2px(context, 31f)
            minHeight = Tools.dp2px(context, 31f)
        } else {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Tools.dp2px(context, 31f))
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