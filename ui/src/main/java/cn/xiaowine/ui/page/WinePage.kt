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
import androidx.lifecycle.ViewModelProvider
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools.dp2px
import cn.xiaowine.ui.annotation.Coroutine
import cn.xiaowine.ui.data.TogglePageDate
import cn.xiaowine.ui.viewmodel.PageViewModel
import cn.xiaowine.ui.widget.WineLine
import cn.xiaowine.ui.widget.WineSwitch
import cn.xiaowine.ui.widget.WineText
import cn.xiaowine.ui.widget.WineTitle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class WinePage : Fragment() {
    private val pageViewModel: PageViewModel by lazy { ViewModelProvider(requireActivity())[PageViewModel::class.java] }

    private val viewList = ArrayList<Pair<Class<out View>, View.() -> Unit>>()

    private val rootView: LinearLayout by lazy {
        LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp2px(context, 28f), 0, dp2px(context, 28f), 0)
            setBackgroundColor(context.getColor(R.color.background_color))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = rootView.let {
        if (this::class.java.getAnnotation(Coroutine::class.java) != null) {
            CoroutineScope(Dispatchers.Main).launch {
                addView2Root()
            }
        } else {
            addView2Root()
        }
        it
    }

    private fun addView2Root() {
        rootView.apply {
            CoroutineScope(Dispatchers.Main).launch {
                viewList.forEach {
                    val view = it.first.getDeclaredConstructor(Context::class.java)
                        .newInstance(requireContext())
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

    fun toPage(page: Class<out WinePage>) {
        Log.d("WinePage", "toPage")
        pageViewModel.nowPage.postValue(TogglePageDate(page, null))
    }

    fun backPage() {
        Log.d("WinePage", "backPage")
        pageViewModel.nowPage.postValue(TogglePageDate(null, this::class.java))
    }
}