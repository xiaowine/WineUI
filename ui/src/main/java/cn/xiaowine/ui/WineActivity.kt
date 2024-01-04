package cn.xiaowine.ui

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cn.xiaowine.ui.Tools.dp2px
import cn.xiaowine.ui.Tools.showView
import cn.xiaowine.ui.data.PageData
import cn.xiaowine.ui.data.TogglePageDate
import cn.xiaowine.ui.databinding.ActivityWineBinding
import cn.xiaowine.ui.page.WinePage
import cn.xiaowine.ui.viewmodel.PageViewModel


open class WineActivity : AppCompatActivity() {
    private val pageViewModel: PageViewModel by lazy { ViewModelProvider(this)[PageViewModel::class.java] }

    val pageItems: MutableList<PageData> = mutableListOf()

    val pageQueue: MutableList<Class<out WinePage>> = mutableListOf()

    private var _binding: ActivityWineBinding? = null
    private val binding get() = _binding!!

    private var savedState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedState = savedInstanceState
        _binding = ActivityWineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.collapsingToolbarLayout.apply {
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
        pageViewModel.nowPage.observe(this) {
            if (it.now == null) {
                pageQueue.remove(it.last)
                if (pageQueue.isEmpty()) {
                    finish()
                    return@observe
                }
                toPage(pageQueue.last(), true)
            } else {
                pageQueue.add(it.now)
                toPage(it.now, false)
            }

        }
        setupOnBackPressedCallback()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun setupOnBackPressedCallback() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (pageQueue.size == 1) {
                    finish()
                    return
                }
                toPage(pageQueue[pageQueue.lastIndex - 1], true)
                pageQueue.remove(pageQueue.last())
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    fun registerPage(vararg page: PageData) {
        pageItems.addAll(page)
        if (pageViewModel.nowPage.value == null) {
            val home = pageItems.singleOrNull { it.isHome } ?: throw Exception("No home page")
            pageViewModel.nowPage.postValue(TogglePageDate(home.page, null))
        }
    }

    private fun toPage(page: Class<out WinePage>, isExit: Boolean) {
        val find = pageItems.find { it.page == page } ?: error("No page")
        binding.apply {
            scrollView.scrollX = 0
            collapsingToolbarLayout.apply {
                collapsedTitleGravity = if (find.isHome) Gravity.CENTER else Gravity.START
                title = find.title ?: getString(find.titleRes)
            }
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(!find.isHome)
                setHomeButtonEnabled(!find.isHome)
            }
        }
        binding.appBar.apply {
            visibility = View.GONE
            showView(400)
        }
        performFragmentTransaction(page, isExit)
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