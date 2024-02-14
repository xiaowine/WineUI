package cn.xiaowine.ui.build

import android.view.View
import cn.xiaowine.ui.page.WinePage
import cn.xiaowine.ui.widget.WineCard
import cn.xiaowine.ui.widget.WineLine
import cn.xiaowine.ui.widget.WineSeekBar
import cn.xiaowine.ui.widget.WineSwitch
import cn.xiaowine.ui.widget.WineText
import cn.xiaowine.ui.widget.WineTitle

class PageBuild {
    val viewList = ArrayList<Pair<Class<out View>, View.() -> Unit>>()

    fun WinePage.toPageText(text: String? = null, page: Class<out WinePage>) {
        text {
            title = text ?: "to ${page.simpleName}"
            onClick {
                toPage(page)
            }
        }
    }

    fun WinePage.toPageText() {
        text {
            title = "backPage"
            onClick {
                backPage()
            }
        }
        line()
    }

    fun text(init: WineText.() -> Unit) {
        viewList.add(Pair(WineText::class.java) { init.invoke(this as WineText) })
    }

    fun line(init: (WineLine.() -> Unit)? = null) {
        viewList.add(Pair(WineLine::class.java) { init?.invoke(this as WineLine) })
    }

    fun switch(init: WineSwitch.() -> Unit) {
        viewList.add(Pair(WineSwitch::class.java) { init.invoke(this as WineSwitch) })
    }

    fun title(init: WineTitle.() -> Unit) {
        viewList.add(Pair(WineTitle::class.java) { init.invoke(this as WineTitle) })
    }

    fun card(init: WineCard.() -> Unit) {
        viewList.add(Pair(WineCard::class.java) { init.invoke(this as WineCard) })
    }

    fun seekbar(init: WineSeekBar.() -> Unit) {
        viewList.add(Pair(WineSeekBar::class.java) { init.invoke(this as WineSeekBar) })
    }

    //
//    fun <T : View> custom(view: View, init: T.() -> Unit) {
//        viewList.add(Pair(view::class.java) { init.invoke(this as T) })
//    }
    fun custom(customView: Class<out View>, init: (View) -> Unit) {
        viewList.add(Pair(customView) { init.invoke(this) })
    }
}