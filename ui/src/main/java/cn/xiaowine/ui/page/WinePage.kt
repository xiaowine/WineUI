package cn.xiaowine.ui.page

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools.dp2px
import cn.xiaowine.ui.widget.WineLine
import cn.xiaowine.ui.widget.WineSwitch
import cn.xiaowine.ui.widget.WineText
import cn.xiaowine.ui.widget.WineTitle

open class WinePage : Fragment() {
    val viewList = ArrayList<Pair<Class<out View>, View.() -> Unit>>()

    val rootView: LinearLayout by lazy {
        LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp2px(context, 28f), 0, dp2px(context, 28f), 0)
            setBackgroundColor(context.getColor(R.color.background_color))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = rootView.apply {
        viewList.forEach {
            val view = it.first.getDeclaredConstructor(Context::class.java).newInstance(requireContext())
            addView(view.apply {
                it.second.invoke(this)
                findViewById<TextView>(R.id.summary_view)?.let { summaryView ->
                    if (summaryView.text.isEmpty()) {
                        summaryView.visibility = View.GONE
                    }
                }
            })
        }
    }

    fun initPage(init: ArrayList<Pair<Class<out View>, View.() -> Unit>>.() -> Unit) {
        Log.d("WinePage", "init")
        viewList.init()
    }

    fun ArrayList<Pair<Class<out View>, View.() -> Unit>>.text(init: WineText.() -> Unit) {
        this.add(Pair(WineText::class.java) { init.invoke(this as WineText) })
    }

    fun ArrayList<Pair<Class<out View>, View.() -> Unit>>.line(init: (WineLine.() -> Unit)? = null) {
        this.add(Pair(WineLine::class.java) { init?.invoke(this as WineLine) })
    }

    fun ArrayList<Pair<Class<out View>, View.() -> Unit>>.switch(init: WineSwitch.() -> Unit) {
        this.add(Pair(WineSwitch::class.java) { init.invoke(this as WineSwitch) })
    }
    fun ArrayList<Pair<Class<out View>, View.() -> Unit>>.title(init: WineTitle.() -> Unit) {
        this.add(Pair(WineTitle::class.java) { init.invoke(this as WineTitle) })
    }
}