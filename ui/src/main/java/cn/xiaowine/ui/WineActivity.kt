package cn.xiaowine.ui

import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cn.xiaowine.ui.appcompat.HyperEditText
import cn.xiaowine.ui.data.PageData
import cn.xiaowine.ui.data.TogglePageDate
import cn.xiaowine.ui.databinding.ActivityWineBinding
import cn.xiaowine.ui.tools.ClassScanner.scanPages
import cn.xiaowine.ui.tools.HyperEditTextFocusTools.hideKeyboardAndClearFocus
import cn.xiaowine.ui.tools.HyperEditTextFocusTools.isShouldHideKeyboard
import cn.xiaowine.ui.tools.HyperEditTextFocusTools.touchIfNeedHideKeyboard
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

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return this.touchIfNeedHideKeyboard(currentFocus, ev) {
            super.dispatchTouchEvent(ev)
        }
    }

    override fun onPause() {
        super.onPause()
        val view = currentFocus
        if (view is HyperEditText) {
            hideKeyboardAndClearFocus(this, view)
        }
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
        allPageData.forEach { newData ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pageItems.removeIf { it.page == newData.page }
            } else {
                val iterator = pageItems.iterator()
                while (iterator.hasNext()) {
                    if (iterator.next().page == newData.page) {
                        iterator.remove()
                    }
                }
            }
        }
        pageItems.addAll(allPageData)
        if (pageViewModel.nowPage.value == null) {
            val home = pageItems.singleOrNull { it.isHome } ?: error("No Found single home page")
            pageViewModel.nowPage.postValue(TogglePageDate(home.page, null))
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun registerPage(packages: String, homePage: Class<out WinePage>) {
        val scanPages = scanPages(this, packages)
        scanPages.forEach {
            if (it != homePage && pageItems.none { item -> item.page == it }) {
                pageItems.add(PageData(it as Class<out WinePage>))
            } else if (it == homePage && pageItems.none { item -> item.page == it && item.isHome }) {
                pageItems.add(PageData(it, isHome = true))
                if (pageViewModel.nowPage.value == null) {
                    pageViewModel.nowPage.postValue(TogglePageDate(it, null))
                }
            }
        }
    }

    private fun performFragmentTransaction(page: Class<out WinePage>, isExit: Boolean) {
        supportFragmentManager.beginTransaction().apply {
            if (isExit) {
                setCustomAnimations(R.animator.slide_left_in, R.animator.slide_right_out, R.animator.slide_right_in, R.animator.slide_left_out)
            } else {
                setCustomAnimations(R.animator.slide_right_in, R.animator.slide_left_out, R.animator.slide_left_in, R.animator.slide_right_out)
            }
        }.replace(R.id.page, page, null).commitNow()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}