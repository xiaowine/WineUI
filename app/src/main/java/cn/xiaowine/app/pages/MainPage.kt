package cn.xiaowine.app.pages

import android.widget.Toast
import cn.xiaowine.ui.WinePage
import cn.xiaowine.ui.data.SpinnerData
import cn.xiaowine.ui.dialog.WineWaitDialog

// 1.继承 WinePage
// 2.初始化页面 initPage

class MainPage : WinePage() {

    // 防止重复初始化
    private var isPageInitialized = false

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
        if (!isPageInitialized) {
            val wineWaitDialog = WineWaitDialog(requireContext()).apply {
                setTitle("加载中")
                show()
            }
            initPage {
                isPageInitialized = true
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
}