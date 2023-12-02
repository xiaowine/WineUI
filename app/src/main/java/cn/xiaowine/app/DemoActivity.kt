package cn.xiaowine.app

import android.os.Bundle
import cn.xiaowine.ui.WineActivity

class DemoActivity : WineActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerPage(APage::class.java)
    }
}