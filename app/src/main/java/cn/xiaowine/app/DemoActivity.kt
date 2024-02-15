package cn.xiaowine.app

import android.os.Bundle
import cn.xiaowine.app.pages.ButtonPage
import cn.xiaowine.app.pages.CardPage
import cn.xiaowine.app.pages.CustomPage
import cn.xiaowine.app.pages.DialogPage
import cn.xiaowine.app.pages.MainPage
import cn.xiaowine.app.pages.SeeKBarPage
import cn.xiaowine.app.pages.SwitchPage
import cn.xiaowine.app.pages.TextPage
import cn.xiaowine.ui.WineActivity
import cn.xiaowine.ui.data.PageData

// 1.继承 WineActivity
// 2.注册页面 registerPage
// 3.注册页面的时候, PageData 应设置 isHome 参数为 true, 表示这个页面是首页
// 4.首页的 TabBar 上不会有返回按钮, 其余页面的 TabBar 上都会有返回按钮
class DemoActivity : WineActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 注册页面，两种方式可以混用，后注册的覆盖先注册的

        // 或通过手动添加注册页面
        registerPage(
            PageData(MainPage::class.java, isHome = true),
            PageData(TextPage::class.java),
            PageData(SwitchPage::class.java),
            PageData(ButtonPage::class.java),
            PageData(CardPage::class.java),
            PageData(CustomPage::class.java),
            PageData(DialogPage::class.java),
            PageData(SeeKBarPage::class.java),
        )

        // 或通过扫描包名注册页面（全部按照默认配置生成界面）
        registerPage("cn.xiaowine.app.pages", MainPage::class.java)
    }
}