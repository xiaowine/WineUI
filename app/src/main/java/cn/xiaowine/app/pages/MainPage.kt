package cn.xiaowine.app.pages

import android.graphics.Color
import android.widget.Toast
import cn.xiaowine.ui.WinePage
import cn.xiaowine.ui.data.SpinnerData
import cn.xiaowine.ui.dialog.WineWaitDialog
import cn.xiaowine.ui.widget.BaseWineText
import cn.xiaowine.ui.widget.WineSpinner

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
                custom(BaseWineText::class.java) {
                    (it as BaseWineText).apply {
                        title = "测试"
                        summary = "测试"
                        addCustomizeView(BaseWineText(requireContext()).apply {
                            title = "测试"
                            summary = "测试"
                        })
                        onClick {
                            Toast.makeText(requireContext(), "测试", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                custom(WineSpinner::class.java) {
                    (it as WineSpinner).apply {
                        setData(
                            SpinnerData("测试1") {
                                Toast.makeText(requireContext(), "测试1", Toast.LENGTH_SHORT).show()
                            },
                            SpinnerData("测试2") {
                                Toast.makeText(requireContext(), "测试2", Toast.LENGTH_SHORT).show()
                            },
                            SpinnerData("测试3") {
                                Toast.makeText(requireContext(), "测试3", Toast.LENGTH_SHORT).show()
                            },
                        )
                    }
                }
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