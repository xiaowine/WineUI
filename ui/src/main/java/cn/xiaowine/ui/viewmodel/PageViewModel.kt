package cn.xiaowine.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.xiaowine.ui.page.WinePage

class PageViewModel : ViewModel() {
    val nowPage: MutableLiveData<Class<out WinePage>> = MutableLiveData()
}