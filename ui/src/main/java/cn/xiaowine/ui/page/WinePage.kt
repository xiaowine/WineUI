package cn.xiaowine.ui.page

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools.dp2px
import cn.xiaowine.ui.WineActivity
import cn.xiaowine.ui.annotation.Coroutine
import cn.xiaowine.ui.build.PageBuild
import cn.xiaowine.ui.data.TogglePageDate
import cn.xiaowine.ui.databinding.FragmentPageBinding
import cn.xiaowine.ui.viewmodel.PageViewModel
import cn.xiaowine.ui.widget.WineCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class WinePage : Fragment() {
    private val pageViewModel: PageViewModel by lazy { ViewModelProvider(requireActivity())[PageViewModel::class.java] }

    private var viewList = ArrayList<Pair<Class<out View>, View.() -> Unit>>()

    private var _binding: FragmentPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentContainer.let {
            if (this::class.java.getAnnotation(Coroutine::class.java) != null) {
                CoroutineScope(Dispatchers.Main).launch {
                    addView2Root()
                }
            } else {
                addView2Root()
            }
        }
    }

//    override fun onDestroy() {
//        Log.e("WinePage", "onDestroy")
//        super.onDestroy()
//    }

    private fun addView2Root() {
        binding.apply {
            toolbar.title = this@WinePage::class.java.simpleName
            fragmentContainer.apply {
                CoroutineScope(Dispatchers.Main).launch {
                    viewList.forEach {
                        if (!isAdded) return@launch
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
            collapsingToolbarLayout.apply {
                expandedTitleTextSize = dp2px(context, 30f).toFloat()
                collapsedTitleTextSize = dp2px(context, 20f).toFloat()
                setCollapsedTitleTypeface(
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        Typeface.create(null, 400, false)
                    } else {
                        Typeface.defaultFromStyle(Typeface.NORMAL)
                    }
                )
                setExpandedTitleTypeface(
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        Typeface.create(null, 300, false)
                    } else {
                        Typeface.defaultFromStyle(Typeface.NORMAL)
                    }
                )
            }
            val wineActivity = activity as WineActivity
            val find = wineActivity.pageItems.find { it.page == wineActivity.pageQueue.last() } ?: return
            binding.apply {
                scrollView.scrollX = 0
                collapsingToolbarLayout.apply {
                    collapsedTitleGravity = if (find.isHome) Gravity.CENTER else Gravity.START
                    title = find.title ?: getString(find.titleRes)
                }
                if (!find.isHome) {
                    toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                }
            }
        }
    }

    fun initPage(init: PageBuild.() -> Unit) {
        viewList = PageBuild().apply(init).viewList
    }

    fun toPage(page: Class<out WinePage>) {
        pageViewModel.nowPage.postValue(TogglePageDate(page, null))
    }

    fun backPage() {
        pageViewModel.nowPage.postValue(TogglePageDate(null, this::class.java))
    }
}