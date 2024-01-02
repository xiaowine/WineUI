package cn.xiaowine.app

import android.os.Bundle
import cn.xiaowine.ui.WineActivity
import cn.xiaowine.ui.data.PageData
import cn.xiaowine.ui.dialog.WineDialog

class DemoActivity : WineActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerPage(
            PageData(APage::class.java, isHome = true),
            PageData(BPage::class.java),
            PageData(CPage::class.java)
        )
        WineDialog(this).apply {
            setTitle("标题")
            setMessage("这是消息")
            addButton("ccc").apply {
                isEnabled = false
            }
            addButton("aaa").apply {
                setCancel(true)
            }
            addButton("ccc")
        }.show()
    }
}