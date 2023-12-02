package cn.xiaowine.ui

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.xiaowine.ui.Tools.dp2px
import cn.xiaowine.ui.databinding.ActivityWineBinding
import cn.xiaowine.ui.page.WinePage

open class WineActivity : AppCompatActivity() {
    val pageItems: MutableList<Class<out WinePage>> = mutableListOf()

    private lateinit var binding: ActivityWineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.collapsingToolbarLayout.apply {
            expandedTitleTextSize = dp2px(context, 30f).toFloat()
            collapsedTitleTextSize = dp2px(context, 18f).toFloat()
            setCollapsedTitleTypeface(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    Typeface.create(null, 400, false)
                } else {
                    Typeface.defaultFromStyle(Typeface.NORMAL)
                }
            )
            setExpandedTitleTypeface(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Typeface.create(null, 300, false)
            } else {
                Typeface.defaultFromStyle(Typeface.NORMAL)
            })
        }
    }

    fun registerPage(vararg page: Class<out WinePage>) {
        page.forEach {
            pageItems.add(it)
        }

        supportFragmentManager.beginTransaction().replace(binding.page.id, pageItems[0], null).commit()
    }
}