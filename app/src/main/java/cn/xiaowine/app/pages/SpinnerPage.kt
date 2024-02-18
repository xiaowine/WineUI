package cn.xiaowine.app.pages

import android.widget.Toast
import cn.xiaowine.ui.WinePage
import cn.xiaowine.ui.data.SpinnerData

class SpinnerPage : WinePage() {
    init {
        initPage {
            toPageText()
            title { text = "Spinner" }
            spinner {
                title = "测试"
                setData(
                    SpinnerData("测试1") {
                        Toast.makeText(requireContext(), "测试1", Toast.LENGTH_SHORT).show()
                    },
                    SpinnerData("测试2") {
                        Toast.makeText(requireContext(), "测试2", Toast.LENGTH_SHORT).show()
                    },
                    SpinnerData("测试3") {
                        Toast.makeText(requireContext(), "测试3", Toast.LENGTH_SHORT).show()
                    },
                )

            }
            spinner {
                title = "测试"
                currentValue = "测试2"
                for (i in 1..10) {
                    setData(
                        SpinnerData("测试$i") {
                            Toast.makeText(requireContext(), "测试$i", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}