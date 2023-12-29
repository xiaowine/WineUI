package cn.xiaowine.ui.build

import android.view.View
import cn.xiaowine.ui.widget.WineCardLink
import cn.xiaowine.ui.widget.WineCardTitle
import cn.xiaowine.ui.widget.WineLine

class CardViewBuild {
    val viewList = ArrayList<Pair<Class<out View>, View.() -> Unit>>()
    fun title(init: WineCardTitle.() -> Unit) {
        viewList.add(Pair(WineCardTitle::class.java) { init.invoke(this as WineCardTitle) })
    }

    fun link(init: WineCardLink.() -> Unit) {
        viewList.add(Pair(WineCardLink::class.java) { init.invoke(this as WineCardLink) })
    }

//    fun line(init: (WineLine.() -> Unit)? = null) {
//        viewList.add(Pair(WineLine::class.java) { init?.invoke(this as WineLine) })
//    }

}