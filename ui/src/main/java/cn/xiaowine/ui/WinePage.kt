package cn.xiaowine.ui

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
import cn.xiaowine.ui.annotation.Coroutine
import cn.xiaowine.ui.build.PageBuild
import cn.xiaowine.ui.data.PageData
import cn.xiaowine.ui.data.TogglePageDate
import cn.xiaowine.ui.databinding.FragmentPageBinding
import cn.xiaowine.ui.tools.Tools.dp
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
    var pageItems: MutableSet<PageData>
        get() = pageViewModel.pageItems.value!!
        set(value) {
            pageViewModel.pageItems.postValue(value)
        }

    var pageQueue: MutableList<Class<out WinePage>>
        get() = pageViewModel.pageQueue.value!!
        set(value) {
            pageViewModel.pageQueue.postValue(value)
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        if (this::class.java.getAnnotation(Coroutine::class.java) != null) {
            CoroutineScope(Dispatchers.Main).launch {
                addView2Root()
            }
        } else {
            addView2Root()
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
                viewList.forEach {
                    if (!isAdded) return
                    val view = it.first.getDeclaredConstructor(Context::class.java).newInstance(requireContext())
                    addView(view.apply {
                        it.second.invoke(this)
                        if (this::class.java.name != WineCard::class.java.name) {
                            setPadding(28.dp, 0, 28.dp, 0)
                        }
                        findViewById<TextView>(R.id.summary_view)?.let { summaryView ->
                            if (summaryView.text.isEmpty()) {
                                summaryView.visibility = View.GONE
                            }
                        }
                    })
                }
                setPadding(0, 0, 0, 30.dp)
            }
            collapsingToolbarLayout.apply {
                expandedTitleTextSize = 30.dp.toFloat()
                collapsedTitleTextSize = 20.dp.toFloat()
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

            val findPage = pageItems.find { it.page == pageQueue.last() } ?: return
            binding.apply {
                collapsingToolbarLayout.apply {
                    collapsedTitleGravity = if (findPage.isHome) Gravity.CENTER else Gravity.START
                    title = findPage.title ?: getString(findPage.titleRes)
                }
                if (!findPage.isHome) {
                    toolbar.apply {
                        setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                        setNavigationOnClickListener {
                            backPage()
                        }
                    }
                }
            }
        }
    }


    fun initPage(init: PageBuild.() -> Unit) {
        val list = PageBuild().apply(init).viewList
        viewList.addAll(list)
    }

    fun toPage(page: Class<out WinePage>) {
        pageViewModel.nowPage.postValue(TogglePageDate(page, null))
    }

    fun backPage() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    fun reloadPage() {
        binding.fragmentContainer.removeAllViews()
        init()
    }
}