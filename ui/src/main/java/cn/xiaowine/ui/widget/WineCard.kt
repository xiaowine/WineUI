package cn.xiaowine.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.tools.Tools.dp2px
import cn.xiaowine.ui.build.CardViewBuild

class WineCard(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : CardView(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, androidx.cardview.R.attr.cardViewStyle)
    constructor(context: Context) : this(context, null)

    private val content: LinearLayout

    init {
        radius = 45f
        cardElevation = 0f
        useCompatPadding = false
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_bg_color))
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
            setMargins(dp2px(context, 15f), dp2px(context, 10f), dp2px(context, 15f), dp2px(context, 10f))
        }
        content = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(0, dp2px(context, 10f), 0, dp2px(context, 16f))
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            this@WineCard.addView(this)
        }
    }

    fun build(init: CardViewBuild.() -> Unit) {
        CardViewBuild().apply(init).viewList.forEach {
            val view = it.first.getConstructor(Context::class.java).newInstance(context).apply {
                setPadding(dp2px(context, 20f), 0, dp2px(context, 20f), 0)
                content.addView(this)
            }
            it.second.invoke(view)
        }
    }
}