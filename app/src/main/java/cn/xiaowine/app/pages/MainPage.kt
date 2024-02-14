package cn.xiaowine.app.pages

import cn.xiaowine.ui.WinePage
import cn.xiaowine.ui.dialog.WineWaitDialog

// 1.继承WinePage
// 2.初始化页面initPage

class MainPage : WinePage() {
    init {
        initPage {
            title {
                text = this@MainPage::class.java.simpleName
            }
            title {
                text = "标题"
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val wineWaitDialog = WineWaitDialog(requireContext()).apply {
            setTitle("加载中")
            show()
        }
        initPage {
            pageItems.forEach {
                if (it.page == this@MainPage::class.java) {
                    return@forEach
                }
                toPageText(page = it.page)
            }
        }
        reloadPage()
        wineWaitDialog.dismiss()

    }
}