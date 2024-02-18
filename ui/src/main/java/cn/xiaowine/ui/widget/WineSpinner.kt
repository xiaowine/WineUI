package cn.xiaowine.ui.widget


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
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
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.appcompat.HyperPopup
import cn.xiaowine.ui.data.SpinnerData
import cn.xiaowine.ui.tools.DrawableTools.createRoundShape
import cn.xiaowine.ui.tools.Tools.dp
import kotlin.properties.Delegates

@SuppressLint("ClickableViewAccessibility")
class WineSpinner(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseWineText(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private val listPopupWindow = HyperPopup(context)

    val item: MutableList<SpinnerData> = mutableListOf()
    var currentValue: String by Delegates.observable("") { _, _, newValue ->
        textView.text = newValue
    }

    private val adapter = object : BaseAdapter() {
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
                val pressedDrawable = createRoundShape(radius, ContextCompat.getColor(context, if (isActive) R.color.popup_select_background_click_color else R.color.popup_background_click_color))
                val normalDrawable = createRoundShape(radius, ContextCompat.getColor(context, if (isActive) R.color.popup_select_background_color else R.color.popup_background_color))
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
                    setTextColor(ContextCompat.getColor(context, if (isActive) R.color.popup_select_text_color else R.color.title_text_color))
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
    }

    private val textView = TextView(context).apply {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
        setTextColor(ContextCompat.getColor(context, R.color.title_color))
        width = 150.dp
        setPadding(30.dp, 0, 5.dp, 0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            paint.typeface = Typeface.create(null, 400, false)
        } else {
            paint.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        }
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
    }
    private val imageView = ImageView(context).apply {
        background = ContextCompat.getDrawable(context, R.drawable.ic_spinner)
    }

    init {
        val linearLayout = LinearLayout(context).apply {
            gravity = Gravity.CENTER_VERTICAL
            addView(textView)
            addView(imageView)
        }
        addCustomizeView(linearLayout)
        setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                listPopupWindow.show()
                performClick()
            }
            true
        }
    }

    fun onItemClick(onItemClick: (item: SpinnerData, position: Int) -> Unit) {
        listPopupWindow.setOnItemClickListener { _, _, position, _ ->
            onItemClick(item[position], position)
        }
    }

    fun setData(vararg data: SpinnerData) {
        item.addAll(data)
        adapter.notifyDataSetChanged()
    }

    fun setData(vararg data: Pair<String, ((Int) -> Unit)?>) {
        data.forEach {
            item.add(SpinnerData(it.first, it.second))
        }
        adapter.notifyDataSetChanged()
    }

    override fun onAttachedToWindow() {
        listPopupWindow.apply {
            anchorView = this@WineSpinner
            if (currentValue.isEmpty()) currentValue = item.first().text
            setAdapter(adapter)
        }
        super.onAttachedToWindow()
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

    interface OnItemClickListener {
        fun onItemClick(item: SpinnerData, position: Int)
    }

}