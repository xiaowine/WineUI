package cn.xiaowine.ui.data

data class SpinnerData(
    val text: String,
    val click: ((Int) -> Unit)? = null
)
