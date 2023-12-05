package cn.xiaowine.ui

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
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
            toPage(it)
        }
    }

    fun registerPage(vararg page: PageData) {
        page.forEach {
            pageItems.add(it)
        }
        val home = pageItems.singleOrNull { it.isHome } ?: throw Exception("No home page")
        pageViewModel.nowPage.value = home.page
    }

    fun toPage(page: Class<out WinePage>) {
        val find = pageItems.find { it.page == page } ?: throw Exception("No page")
        supportFragmentManager
            .beginTransaction()
            .replace(binding.page.id, page, null)
            .commit()
        binding.apply {
            collapsingToolbarLayout.apply {
                title = find.title ?: getString(find.titleRes)
            }
        }
    }
}