package cn.xiaowine.app.pages

import android.annotation.SuppressLint
import android.widget.Toast
import cn.xiaowine.ui.dialog.WineDialog
import cn.xiaowine.ui.dialog.WineEditTextDialog
import cn.xiaowine.ui.dialog.WineProgressBar
import cn.xiaowine.ui.WinePage
@SuppressLint("SetTextI18n")
class DialogPage : WinePage() {

    init {
        initPage {
            toPageText()
            title { text = "Dialog" }
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
            text {
                title = "Show WineProgressBar"
                onClick {
                    WineProgressBar(requireContext()).apply {
                        setTitle("标题")
                        show()
                    }
                }
            }
            text {
                title = "Show WineEditTextDialog"
                onClick {
                    WineEditTextDialog(context).apply {
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
        }
    }
}