package cn.xiaowine.app

import android.os.Bundle
import cn.xiaowine.app.pages.MainPage
import cn.xiaowine.app.pages.SwitchPage
import cn.xiaowine.app.pages.TextPage
import cn.xiaowine.ui.WineActivity
import cn.xiaowine.ui.data.PageData

// 1.继承WineActivity
// 2.注册页面registerPage
// 3.注册页面的时候，PageData参数可以设置isHome为true，表示这个页面是首页
// 首页在tabbar不会有返回按钮，其余页面都会有返回按钮
class DemoActivity : WineActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerPage(
            PageData(MainPage::class.java, isHome = true),
            PageData(TextPage::class.java),
            PageData(SwitchPage::class.java)
        )
    }
}