package cn.xiaowine.app

import android.widget.SeekBar
import android.widget.Toast
import cn.xiaowine.ui.dialog.WineDialog
import cn.xiaowine.ui.page.WinePage
import cn.xiaowine.ui.widget.WineSeekBar

class APage : WinePage() {

    init {
        initPage {
            title {
                text = this@APage::class.java.simpleName
            }
            title {
                text = "标题"
            }
            text {
                title = "to BPage"
                onClick {
                    toPage(BPage::class.java)
                }
            }
            text {
                title = "to CPage"
                onClick {
                    toPage(CPage::class.java)
                }
            }
            text {
                title = "这是一个标题"
            }
            text {
                setIcon(R.drawable.ic_launcher_background)
                title = "这是一个带图标的标题"
            }
            text {
                title = "这是一个带摘要的标题"
                summary = "这是一个摘要"
            }
            text {
                title = "这是一个带图标带摘要的标题"
                summary = "这是一个摘要"
                setIcon(R.drawable.ic_launcher_background)
            }
            text {
                title = "带摘要的标题"
                summary =
                    "这是摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘要"
            }
            text {
                title = "带摘要带图标的标题"
                summary =
                    "这是摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘摘要"
                setIcon(R.drawable.ic_launcher_background)
            }
            line()
            title {
                text = "开关"
            }
            switch {
                title = "开关"
                isChecked = true
                onClick {
                    Toast.makeText(context, "onClick value:$it", Toast.LENGTH_SHORT).show()
                }
                onLongClick {
                    Toast.makeText(context, "onLongClick value:$it", Toast.LENGTH_SHORT).show()
                }
            }
            switch {
                title = "开关"
                onClick {
                    Toast.makeText(context, "onClick value:$it", Toast.LENGTH_SHORT).show()
                }
                onLongClick {
                    Toast.makeText(context, "onLongClick value:$it", Toast.LENGTH_SHORT).show()
                }
                setIcon(R.drawable.ic_launcher_background)
            }
            switch {
                title = "带摘要的开关"
                summary = "这是一个摘要"
                isChecked = true
                onClick {
                    Toast.makeText(context, "onClick value:$it", Toast.LENGTH_SHORT).show()
                }
                onLongClick {
                    Toast.makeText(context, "onLongClick value:$it", Toast.LENGTH_SHORT).show()
                }
            }
            switch {
                title = "带摘要带图标的开关"
                summary = "这是一个摘要"
                isChecked = false
                onClick {
                    Toast.makeText(context, "onClick value:$it", Toast.LENGTH_SHORT).show()
                }
                onLongClick {
                    Toast.makeText(context, "onLongClick value:$it", Toast.LENGTH_SHORT).show()
                }
                setIcon(R.drawable.ic_launcher_background)
            }
            line()
            title {
                text = "SeekBar"
            }
            seekbar {
                minProgress = -200
                maxProgress = 250
                onProgressChanged(object : WineSeekBar.ProgressChangedListener {
                    override fun onChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                        Toast.makeText(context, "progress:$progress", Toast.LENGTH_SHORT).show()
                    }
                })
                onLongClick { _, _ ->
                    Toast.makeText(context, "onLongClick", Toast.LENGTH_SHORT).show()
                }
            }
            line()

            text {
                title = "Show Dialog"
                onClick {
                    WineDialog(context).apply {
                        setTitle("标题")
                        setMessage("这是一个消息")
                        addButton("确定") {
                            Toast.makeText(context, "确定", Toast.LENGTH_SHORT).show()
                            dismiss()
                        }
                        addButton("取消") {
                            Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show()
                            dismiss()
                        }.setCancel(true)
                        show()
                    }
                }
            }
            text {
                title = "Show Dialog"
                onClick {
                    WineDialog(context).apply {
                        setTitle("标题")
                        setMessage("这是一个消息")
                        addButton("确定") {
                            Toast.makeText(context, "确定", Toast.LENGTH_SHORT).show()
                            dismiss()
                        }
                        addButton("取消") {
                            Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show()
                            dismiss()
                        }.isEnabled = false
                        show()
                    }
                }
            }
            text {
                title = "Show Dialog"
                onClick {
                    WineDialog(context).apply {
                        setTitle("标题")
                        setMessage("这是一个消息")
                        addButton("确定") {
                            Toast.makeText(context, "确定", Toast.LENGTH_SHORT).show()
                            dismiss()

                        }
                        addButton("1取消") {
                            Toast.makeText(context, "1取消", Toast.LENGTH_SHORT).show()
                            dismiss()
                        }
                        addButton("2取消") {
                            Toast.makeText(context, "2取消", Toast.LENGTH_SHORT).show()
                            dismiss()
                        }
                        show()
                    }
                }
            }
            line()

            card {
                build {
                    title {
                        text = "WineCardTitle"
                    }
                    link {
                        title = "链接"
                    }
                    link {
                        title = "链接"
                    }
                }
            }

        }
    }
}