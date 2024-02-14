package cn.xiaowine.ui.data

import androidx.annotation.StringRes
import cn.xiaowine.ui.WinePage

class PageData() {
    lateinit var page: Class<out WinePage>
    var title: String? = null

    @StringRes
    var titleRes: Int = -1
    var isHome: Boolean = false
//    下面俩参数暂未实现
    var showMenu: Boolean = false
    var useCatch: Boolean = false

    constructor(
        page: Class<out WinePage>,
        title: String? = null,
        @StringRes
        titleRes: Int = -1,
        isHome: Boolean = false,
        showMenu: Boolean = false,
        useCatch: Boolean = false
    ) : this() {
        this.page = page
        this.title = title ?: page.simpleName
        this.titleRes = titleRes
        this.isHome = isHome
        this.showMenu = showMenu
        this.useCatch = useCatch
    }

    constructor(page: Class<out WinePage>) : this() {
        this.page = page
        this.title = page.simpleName
    }

    constructor(page: Class<out WinePage>, title: String) : this() {
        this.page = page
        this.title = title
    }

    constructor(page: Class<out WinePage>,@StringRes titleRes: Int) : this() {
        this.page = page
        this.titleRes = titleRes
    }
}