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
import cn.xiaowine.ui.build.PageBuild
import cn.xiaowine.ui.data.TogglePageDate
import cn.xiaowine.ui.viewmodel.PageViewModel
import cn.xiaowine.ui.widget.WineCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class WinePage : Fragment() {
    private val pageViewModel: PageViewModel by lazy { ViewModelProvider(requireActivity())[PageViewModel::class.java] }

    private var viewList = ArrayList<Pair<Class<out View>, View.() -> Unit>>()

    private val rootView: LinearLayout by lazy {
        LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(context.getColor(R.color.background_color))
            setPadding(0, 0, 0, dp2px(context, 30f))
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

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun addView2Root() {
        rootView.apply {
            CoroutineScope(Dispatchers.Main).launch {
                viewList.forEach {
                    if (!isAdded)return@launch
                    val view = it.first.getDeclaredConstructor(Context::class.java)
                        .newInstance(requireContext())
                    addView(view.apply {
                        it.second.invoke(this)
                        if (this::class.java.name != WineCard::class.java.name) setPadding(dp2px(context, 28f), 0, dp2px(context, 28f), 0)
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

    fun initPage(init: PageBuild.() -> Unit) {
        Log.d("WinePage", "init")
        viewList = PageBuild().apply(init).viewList
    }

    fun toPage(page: Class<out WinePage>) {
        pageViewModel.nowPage.postValue(TogglePageDate(page, null))
    }

    fun backPage() {
        pageViewModel.nowPage.postValue(TogglePageDate(null, this::class.java))
    }
}