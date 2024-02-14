package cn.xiaowine.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.xiaowine.ui.data.PageData
import cn.xiaowine.ui.data.TogglePageDate
import cn.xiaowine.ui.WinePage

class PageViewModel : ViewModel() {
    val nowPage: MutableLiveData<TogglePageDate> = MutableLiveData()
    val pageItems: MutableLiveData<MutableList<PageData>> = MutableLiveData(mutableListOf())
    val pageQueue: MutableLiveData<MutableList<Class<out WinePage>>> = MutableLiveData(mutableListOf())
}