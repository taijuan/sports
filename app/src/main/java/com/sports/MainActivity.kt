package com.sports

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.sports.adapter.NewsAdapter
import com.sports.api.Error
import com.sports.api.Success
import com.sports.api.rongYunService
import com.sports.base.BaseActivity
import com.sports.model.PageState
import com.sports.model.UserToken
import com.sports.utils.EncryptUtils
import com.sports.utils.logE
import com.sports.viewmodel.NewsDataViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_refresh_and_load_more.*
import java.lang.Math.random

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        topBar.setTitle("Main")
        val adapter = NewsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val newsDataViewModel = ViewModelProviders.of(this).get(NewsDataViewModel::class.java)
        newsDataViewModel.firstData.observe(this, Observer {
            when (it.state) {
                PageState.Empty -> {
                    emptyView.show(false, "数据为空", null, "点击重试") {
                        newsDataViewModel.firstLoad(this, "asia", 1)
                        emptyView.show(true)
                    }
                }
                PageState.LoadMore -> {
                    emptyView.hide()
                    adapter.refresh(it.dataList)
                    smartRefreshLayout.finishRefresh()
                    smartRefreshLayout.finishLoadMore()
                }
                PageState.LoadMoreNoData -> {
                    emptyView.hide()
                    adapter.refresh(it.dataList)
                    smartRefreshLayout.finishRefresh()
                    smartRefreshLayout.finishLoadMoreWithNoMoreData()
                }
                PageState.Error -> {
                    emptyView.show(false, "加载失败", null, "点击重试") {
                        newsDataViewModel.firstLoad(this, "asia", 1)
                        emptyView.show(true)
                    }
                }
            }
        })
        newsDataViewModel.refreshData.observe(this, Observer {
            when (it.state) {
                PageState.LoadMore -> {
                    adapter.refresh(it.dataList)
                    smartRefreshLayout.finishRefresh()
                    smartRefreshLayout.finishLoadMore()
                }
                PageState.LoadMoreNoData -> {
                    adapter.refresh(it.dataList)
                    smartRefreshLayout.finishRefresh()
                    smartRefreshLayout.finishLoadMoreWithNoMoreData()
                }
                else -> {
                    smartRefreshLayout.finishRefresh(false)
                }
            }
        })
        newsDataViewModel.loadMoreData.observe(this, Observer {
            when (it.state) {
                PageState.LoadMore -> {
                    adapter.loadMore(it.dataList)
                    smartRefreshLayout.finishLoadMore()
                }
                PageState.LoadMoreNoData -> {
                    adapter.loadMore(it.dataList)
                    smartRefreshLayout.finishLoadMoreWithNoMoreData()
                }
                else -> {
                    smartRefreshLayout.finishLoadMore(false)
                }
            }
        })
        emptyView.show(true)
        emptyView.post {
            newsDataViewModel.firstLoad(this, "asia", 1)
        }
        smartRefreshLayout.setOnRefreshListener {
            newsDataViewModel.refresh(this, "asia", 1)
        }
        smartRefreshLayout.setOnLoadMoreListener {
            newsDataViewModel.loadMore(this, "asia", 1)
        }

        test()
    }

    private fun test() {
        val key = "sfci50a7s30ti"
        val round = (random() * 10000).toInt().toString()
        val time = System.currentTimeMillis().toString()
        val signature = EncryptUtils.encryptSHA1ToString("$key$round$time")
        rongYunService.getUserToken(key, time, round, signature, "zuiweng").observe(this, Observer {
            when (it) {
                is Success<UserToken> -> {
                    it.logE()
                }
                is Error<UserToken> -> {
                    it.errorMsg.logE()
                }
            }
        })
    }

}
