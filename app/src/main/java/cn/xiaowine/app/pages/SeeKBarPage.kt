package cn.xiaowine.app.pages

import android.annotation.SuppressLint
import android.widget.SeekBar
import android.widget.Toast
import cn.xiaowine.ui.WinePage
import cn.xiaowine.ui.widget.WineSeekBar

@SuppressLint("SetTextI18n")
class SeeKBarPage : WinePage() {

    init {
        initPage {
            toPageText()
            title {
                text = "SeekBar"
            }
            title {
                text = "SeekBar的最大值为9999，最小值为-9999，Android 8.0以上才支持设置最小值"
            }
            seekbar {
                minProgress = -200
                maxProgress = 200
                onProgressChanged(object : WineSeekBar.ProgressChangedListener {
                    override fun onChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                        if (fromUser) Toast.makeText(context, "progress:$progress", Toast.LENGTH_SHORT).show()
                    }
                })
                onLongClick { _, _ ->
                    Toast.makeText(context, "onLongClick", Toast.LENGTH_SHORT).show()
                }
            }
            seekbar {
                nowProgress = 101
                minProgress = 0
                maxProgress = 1000
                onProgressChanged(object : WineSeekBar.ProgressChangedListener {
                    override fun onChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                        if (fromUser) Toast.makeText(context, "progress:$progress", Toast.LENGTH_SHORT).show()
                    }
                })
                onLongClick { _, _ ->
                    Toast.makeText(context, "onLongClick", Toast.LENGTH_SHORT).show()
                }
            }
            seekbar {
                nowProgress = -9999
                minProgress = -9999
                maxProgress = 9999
                onProgressChanged(object : WineSeekBar.ProgressChangedListener {
                    override fun onChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                        if (fromUser) Toast.makeText(context, "progress:$progress", Toast.LENGTH_SHORT).show()
                    }
                })
                onLongClick { _, _ ->
                    Toast.makeText(context, "onLongClick", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}