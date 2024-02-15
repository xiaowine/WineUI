package cn.xiaowine.ui.widget


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.appcompat.HyperButton
import cn.xiaowine.ui.appcompat.HyperPopup
import cn.xiaowine.ui.data.SpinnerData
import cn.xiaowine.ui.tools.DrawableTools.createRoundShape
import cn.xiaowine.ui.tools.Tools.dp

@SuppressLint("ClickableViewAccessibility")
class WineSpinner(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private val listPopupWindow = HyperPopup(context)

    val item: MutableList<SpinnerData> = mutableListOf()
    var currentValue: String = ""

    init {
        orientation = VERTICAL
        addView(HyperButton(context).apply {
            text = "测试"
            setOnTouchListener { view, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    listPopupWindow.apply {
                        if (view.width / 2 >= motionEvent.x) {
                            horizontalOffset = 24.dp
                            setDropDownGravity(Gravity.LEFT)
                        } else {
                            horizontalOffset = (-24).dp
                            setDropDownGravity(Gravity.RIGHT)
                        }
                        show()
                    }
                }
                false
            }
        })
    }

    fun setData(vararg data: SpinnerData) {
        item.addAll(data)
    }

    fun setData(vararg data: Pair<String, ((Int) -> Unit)?>) {
        data.forEach {
            item.add(SpinnerData(it.first, it.second))
        }
    }

    override fun onAttachedToWindow() {
        if (currentValue.isEmpty()) currentValue = item.first().text
        listPopupWindow.apply {
            anchorView = this@WineSpinner
            loadAdapter()
        }
        super.onAttachedToWindow()
        listPopupWindow.apply {
            horizontalOffset = 24.dp
            setDropDownGravity(Gravity.LEFT)
            show()
        }
    }

    fun loadAdapter() {
        listPopupWindow.setAdapter(null)
        listPopupWindow.setAdapter(object : BaseAdapter() {
            override fun getCount(): Int {
                return item.size
            }

            override fun getItem(position: Int): Any {
                return item[position].text
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                val spinnerData = item[position]
                return LinearLayout(context).apply {
                    val itemText = spinnerData.text
                    val isActive = itemText == currentValue
                    val radius = getRadius(position, item.size, 20f.dp)
                    val pressedDrawable = createRoundShape(radius, Color.parseColor(if (isActive) "#D4DFEB" else "#EBEBEB"))
                    val normalDrawable = createRoundShape(radius, Color.parseColor(if (isActive) "#E6F2FF" else "#FFFFFF"))
                    background = createStateListDrawable(pressedDrawable, pressedDrawable, normalDrawable)
                    val textview = (object : AppCompatTextView(context) {
                        init {
                            isFocusable = true
                        }

                        override fun isFocused(): Boolean {
                            return true
                        }
                    }).apply {
                        layoutParams = LayoutParams(100.dp, LayoutParams.WRAP_CONTENT)
                        descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                        setPadding(25.dp, 25.dp, 10.dp, 25.dp)
                        width = 105.dp
                        isSingleLine = true
                        text = itemText
                        ellipsize = TextUtils.TruncateAt.MARQUEE
                        paint.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                        setTextColor(Color.parseColor(if (isActive) "#1385FF" else "#000000"))
                    }
                    addView(textview)
                    if (isActive) {
                        addView(ImageView(context).apply {
                            gravity = Gravity.CENTER_VERTICAL
                            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                                setMargins(0, 0, 20.dp, 0)
                            }
                            background = ContextCompat.getDrawable(context, R.drawable.ic_popup_select)
                        })
                    }
                    setOnClickListener {
                        currentValue = spinnerData.text
                        spinnerData.click?.invoke(position)
                        listPopupWindow.dismiss()
                    }
                }
            }
        })
    }

    fun createStateListDrawable(focusedDrawable: Drawable, pressedDrawable: Drawable, normalDrawable: Drawable): StateListDrawable {
        return StateListDrawable().apply {
            addState(intArrayOf(android.R.attr.state_focused), focusedDrawable)
            addState(intArrayOf(android.R.attr.state_pressed), pressedDrawable)
            addState(intArrayOf(), normalDrawable)
        }
    }

    fun getRadius(position: Int, itemSize: Int, radiusFloat: Float): FloatArray {
        return when {
            itemSize == 1 -> floatArrayOf(radiusFloat, radiusFloat, radiusFloat, radiusFloat)
            position == 0 -> floatArrayOf(radiusFloat, radiusFloat, 0f, 0f)
            position == itemSize - 1 -> floatArrayOf(0f, 0f, radiusFloat, radiusFloat)
            else -> floatArrayOf(0f, 0f, 0f, 0f)
        }
    }
}