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
    val firstData = MutableLiveData<List<News>>()
    val refreshData = MutableLiveData<List<News>>()
    val loadMoreData = MutableLiveData<List<News>>()
    val state = MutableLiveData<PageState>()

    @MainThread
    fun firstLoad(owner: LifecycleOwner, code: String, type: Int) {
        sportService.getUser(code, 1, type).observe(owner, Observer {
            when (it) {
                is Success<BaseResObj<PageData<News>>> -> {
                    val a = it.body.data
                    this.data = a
                    if (!a.dataList.isNullOrEmpty()) {
                        firstData.postValue(a.dataList)
                    }
                    when {
                        a.dataList.isNullOrEmpty() -> state.postValue(PageState.FirstEmpty)
                        a.totalPage > a.curPage -> state.postValue(PageState.FirstLoadMore)
                        else -> state.postValue(PageState.FirstLoadMoreNoData)
                    }
                }
                is Error<BaseResObj<PageData<News>>> -> {
                    state.postValue(PageState.FirstError)
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
                    if (!a.dataList.isNullOrEmpty()) {
                        refreshData.postValue(a.dataList)
                    }
                    when {
                        a.dataList.isNullOrEmpty() -> state.postValue(PageState.RefreshEmpty)
                        a.totalPage > a.curPage -> state.postValue(PageState.RefreshLoadMore)
                        else -> state.postValue(PageState.RefreshLoadMoreNoData)
                    }
                }
                is Error<BaseResObj<PageData<News>>> -> {
                    state.postValue(PageState.RefreshError)
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
                    if (!a.dataList.isNullOrEmpty()) {
                        loadMoreData.postValue(a.dataList)
                    }
                    when {
                        a.totalPage > a.curPage -> state.postValue(PageState.LoadMore)
                        else -> state.postValue(PageState.LoadMoreNoData)
                    }
                }
                is Error<BaseResObj<PageData<News>>> -> {
                    state.postValue(PageState.LoadError)
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