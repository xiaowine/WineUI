package cn.xiaowine.ui

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cn.xiaowine.ui.Tools.dp2px
import cn.xiaowine.ui.data.PageData
import cn.xiaowine.ui.databinding.ActivityWineBinding
import cn.xiaowine.ui.page.WinePage
import cn.xiaowine.ui.viewmodel.PageViewModel


open class WineActivity : AppCompatActivity() {
    val pageViewModel: PageViewModel by lazy { ViewModelProvider(this)[PageViewModel::class.java] }

    val pageItems: MutableList<PageData> = mutableListOf()

    val pageQueue: MutableList<Class<out WinePage>> = mutableListOf()

    private lateinit var binding: ActivityWineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWineBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            pageQueue.add(it)
            toPage(it)
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (pageQueue.size==1) {
                    finish()
                    return
                }
                toPage(pageQueue[pageQueue.lastIndex - 1])
                pageQueue.remove(pageQueue.last())
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    fun registerPage(vararg page: PageData) {
        page.forEach {
            pageItems.add(it)
        }
        val home = pageItems.singleOrNull { it.isHome } ?: throw Exception("No home page")
        pageViewModel.nowPage.postValue(home.page)
    }

    fun toPage(page: Class<out WinePage>) {
        val find = pageItems.find { it.page == page } ?: throw Exception("No page")
        binding.apply {
            scrollView.scrollX = 0
            collapsingToolbarLayout.apply {
                title = find.title ?: getString(find.titleRes)
            }
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.page, page, null)
            .commit()
    }
}