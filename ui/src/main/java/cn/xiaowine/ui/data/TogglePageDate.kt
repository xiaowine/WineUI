package cn.xiaowine.ui.data

import cn.xiaowine.ui.page.WinePage

data class TogglePageDate(
    val now: Class<out WinePage>?,
    val last: Class<out WinePage>?
)