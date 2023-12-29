package cn.xiaowine.ui.build

import android.view.View
import cn.xiaowine.ui.widget.WineCard
import cn.xiaowine.ui.widget.WineLine
import cn.xiaowine.ui.widget.WineSwitch
import cn.xiaowine.ui.widget.WineText
import cn.xiaowine.ui.widget.WineTitle

class PageBuild {
    val viewList = ArrayList<Pair<Class<out View>, View.() -> Unit>>()

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
}