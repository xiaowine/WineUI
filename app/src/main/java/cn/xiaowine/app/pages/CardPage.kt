package cn.xiaowine.app.pages

import android.annotation.SuppressLint
import android.widget.Toast
import cn.xiaowine.ui.WinePage
@SuppressLint("SetTextI18n")
class CardPage  : WinePage() {

    init {
        initPage {
            toPageText()
            title { text = "Card" }
            card {
                build {
                    title {
                        text = "WineCardTitle"
                    }
                    link {
                        title = "链接"
                        onClick {
                            Toast.makeText(context, "onClick", Toast.LENGTH_SHORT).show()
                        }
                    }
                    link {
                        title = "链接"
                    }
                }
            }
            card {
                build {
                    title {
                        text = "WineCardTitle"
                    }
                    link {
                        title = "链接"
                        onClick {
                            Toast.makeText(context, "onClick", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }
    }
}
