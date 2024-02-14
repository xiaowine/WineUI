package cn.xiaowine.app.pages

import cn.xiaowine.ui.WinePage

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
            toPageText(page = TextPage::class.java)
            toPageText(page = SwitchPage::class.java)
            toPageText(page = SeeKBarPage::class.java)
            toPageText(page = DialogPage::class.java)
            toPageText(page = CustomPage::class.java)
            toPageText(page = CardPage::class.java)
        }
    }
}