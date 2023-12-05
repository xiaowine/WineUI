package cn.xiaowine.app

import android.os.Bundle
import cn.xiaowine.ui.WineActivity
import cn.xiaowine.ui.data.PageData

class DemoActivity : WineActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerPage(
            PageData(APage::class.java, isHome = true),
            PageData(BPage::class.java)
        )
    }
}