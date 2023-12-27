package cn.xiaowine.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.xiaowine.ui.data.TogglePageDate

class PageViewModel : ViewModel() {
    val nowPage: MutableLiveData<TogglePageDate> = MutableLiveData()
}