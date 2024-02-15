package cn.xiaowine.ui.data

import androidx.annotation.StringRes
import cn.xiaowine.ui.WinePage

data class PageData(
    var page: Class<out WinePage>,
    var title: String? = null,
    @StringRes
    var titleRes: Int = -1,
    var isHome: Boolean = false,
    var showMenu: Boolean = false,
    var useCatch: Boolean = false
) {
    init {
        this.title = title ?: page.simpleName
    }
}