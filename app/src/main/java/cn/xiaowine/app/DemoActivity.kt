package cn.xiaowine.app

import android.os.Bundle
import cn.xiaowine.app.pages.MainPage
import cn.xiaowine.app.pages.SwitchPage
import cn.xiaowine.app.pages.TextPage
import cn.xiaowine.ui.WineActivity
import cn.xiaowine.ui.data.PageData

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