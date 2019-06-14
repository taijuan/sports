package com.sports.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.sports.api.Error
import com.sports.api.Success
import com.sports.api.sportService
import com.sports.model.BaseResObj
import com.sports.model.News
import com.sports.model.PageData
import com.sports.model.PageState
import com.sports.utils.logInfo

class NewsDataViewModel : ViewModel() {
    private var data: PageData<News> = PageData(mutableListOf(), 1, 1)
    val firstData = MutableLiveData<PageData<News>>()
    val refreshData = MutableLiveData<PageData<News>>()
    val loadMoreData = MutableLiveData<PageData<News>>()

    @MainThread
    fun firstLoad(owner: LifecycleOwner, code: String, type: Int) {
        sportService.getUser(code, 1, type).observe(owner, Observer {
            when (it) {
                is Success<BaseResObj<PageData<News>>> -> {
                    val a = it.body.data
                    this.data = a
                    when {
                        a.dataList.isNullOrEmpty() -> a.state = PageState.Empty
                        a.totalPage > a.curPage -> a.state = PageState.LoadMore
                        else -> a.state = PageState.LoadMore
                    }
                    firstData.postValue(a)
                }
                is Error<BaseResObj<PageData<News>>> -> {
                    this.data.errorMsg = it.errorMsg
                    this.data.state = PageState.Error
                    firstData.postValue(this.data)
                }
            }
        })
    }

    @MainThread
    fun refresh(owner: LifecycleOwner, code: String, type: Int) {
        sportService.getUser(code, 1, type).observe(owner, Observer {
            when (it) {
                is Success<BaseResObj<PageData<News>>> -> {
                    val a = it.body.data
                    this.data = a
                    when {
                        a.totalPage > a.curPage -> a.state = PageState.LoadMore
                        else -> a.state = PageState.LoadMore
                    }
                    refreshData.postValue(a)
                }
                is Error<BaseResObj<PageData<News>>> -> {
                    this.data.errorMsg = it.errorMsg
                    this.data.state = PageState.Error
                    refreshData.postValue(this.data)
                }
            }
        })
    }

    @MainThread
    fun loadMore(owner: LifecycleOwner, code: String, type: Int) {
        sportService.getUser(code, data.curPage + 1, type).observe(owner, Observer {
            when (it) {
                is Success<BaseResObj<PageData<News>>> -> {
                    val a = it.body.data
                    this.data = a
                    when {
                        a.totalPage > a.curPage -> a.state = PageState.LoadMore
                        else -> a.state = PageState.LoadMore
                    }
                    loadMoreData.postValue(a)
                }
                is Error<BaseResObj<PageData<News>>> -> {
                    this.data.errorMsg = it.errorMsg
                    this.data.state = PageState.Error
                    loadMoreData.postValue(this.data)
                }
            }
        })
    }

    override fun onCleared() {
        data.dataList.clear()
        "$javaClass ->onCleared".logInfo()
        super.onCleared()
    }
}