package cn.xiaowine.ui

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cn.xiaowine.ui.data.PageData
import cn.xiaowine.ui.data.TogglePageDate
import cn.xiaowine.ui.databinding.ActivityWineBinding
import cn.xiaowine.ui.tools.ClassScanner.scanPages
import cn.xiaowine.ui.viewmodel.PageViewModel


open class WineActivity : AppCompatActivity() {
    private val pageViewModel: PageViewModel by lazy { ViewModelProvider(this)[PageViewModel::class.java] }
    private var pageItems: MutableSet<PageData>
        get() = pageViewModel.pageItems.value!!
        set(value) {
            pageViewModel.pageItems.postValue(value)
        }

    var pageQueue: MutableList<Class<out WinePage>>
        get() = pageViewModel.pageQueue.value!!
        set(value) {
            pageViewModel.pageQueue.postValue(value)
        }

    private var _binding: ActivityWineBinding? = null
    private val binding get() = _binding!!

    private var isHeavyLoad: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isHeavyLoad = savedInstanceState != null
        _binding = ActivityWineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pageViewModel.nowPage.observe(this) {
            if (it.now == null) {
                pageQueue.remove(it.last)
                if (pageQueue.isEmpty()) {
                    finish()
                    return@observe
                }
                performFragmentTransaction(pageQueue.last(), true)
            } else {
                if (pageItems.none { item -> item.page == it.now }) {
                    error("Page not found")
                }
                if (!isHeavyLoad) {
                    pageQueue.add(it.now)
                }
                performFragmentTransaction(it.now, false)
            }

        }
        setupOnBackPressedCallback()
    }


    private fun setupOnBackPressedCallback() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (pageQueue.size == 1) {
                    finish()
                    return
                }
                pageViewModel.nowPage.postValue(TogglePageDate(null, pageQueue.last()))
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    fun registerPage(vararg allPageData: PageData) {
        pageItems.removeIf { pageItem ->
            allPageData.any { it.page == pageItem.page }
        }
        pageItems.addAll(allPageData)
        if (pageViewModel.nowPage.value == null) {
            val home = pageItems.singleOrNull { it.isHome } ?: error("No home page")
            pageViewModel.nowPage.postValue(TogglePageDate(home.page, null))
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun registerPage(packages: String, homePage: Class<out WinePage>) {
        val scanPages = scanPages(this, packages)
        pageItems.removeIf { pageItem ->
           scanPages.any { it == pageItem.page }
        }
        scanPages.forEach {
            if (it == homePage) {
                return@forEach
            }
            pageItems.add(PageData(it as Class<out WinePage>))
        }

        pageItems.add(PageData(homePage, isHome = true))
    }

    private fun performFragmentTransaction(page: Class<out WinePage>, isExit: Boolean) {
        supportFragmentManager
            .beginTransaction().apply {
                if (isExit) {
                    setCustomAnimations(R.animator.slide_left_in, R.animator.slide_right_out, R.animator.slide_right_in, R.animator.slide_left_out)
                } else {
                    setCustomAnimations(R.animator.slide_right_in, R.animator.slide_left_out, R.animator.slide_left_in, R.animator.slide_right_out)
                }
            }
            .replace(R.id.page, page, null)
            .commitNow()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}