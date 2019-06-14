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
import com.sports.api.wangYiService
import com.sports.base.BaseActivity
import com.sports.model.PageState
import com.sports.model.WangYiBaseRes
import com.sports.model.WangYiUser
import com.sports.model.YongYunUser
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
        testWY()
        testWYCreateChatRoom()
    }

    private fun test() {
        val key = "sfci50a7s30ti"
        val secret = "yfBWB8ufYn82G"
        val round = (random() * 10000).toInt().toString()
        val time = System.currentTimeMillis().toString()
        val signature = EncryptUtils.encryptSHA1ToString("$secret$round$time")
        rongYunService.getUserToken(key, time, round, signature, "zuiweng").observe(this, Observer {
            when (it) {
                is Success<YongYunUser> -> {
                    it.logE()
                }
                is Error<YongYunUser> -> {
                    it.errorMsg.logE()
                }
            }
        })
    }

    private fun testWY() {
        val key = "a1d683a6e5e9fae6b89d9868e3efedec"
        val secret = "a1ac6e0812a8"
        val nonce = (random() * 10000).toInt().toString()
        val curTime = System.currentTimeMillis().toString()
        val sha1 = EncryptUtils.encryptSHA1ToString("$secret$nonce$curTime").toLowerCase()
        wangYiService.createWYUser(key, curTime, nonce, sha1, "zuiweng2").observe(this, Observer {
            when (it) {
                is Success<WangYiBaseRes<WangYiUser>> -> {
                    it.logE()
                }
                is Error<WangYiBaseRes<WangYiUser>> -> {
                    it.errorMsg.logE()
                }
            }
        })
    }

    private fun testWYCreateChatRoom() {
        val key = "a1d683a6e5e9fae6b89d9868e3efedec"
        val secret = "a1ac6e0812a8"
        val nonce = (random() * 10000).toInt().toString()
        val curTime = System.currentTimeMillis().toString()
        val sha1 = EncryptUtils.encryptSHA1ToString("$secret$nonce$curTime").toLowerCase()
        wangYiService.createChatRoom(key, curTime, nonce, sha1, "zuiweng", "zuiweng's chat room")
            .observe(this, Observer {
                when (it) {
                    is Success<WangYiBaseRes<WangYiUser>> -> {
                        it.logE()
                    }
                    is Error<WangYiBaseRes<WangYiUser>> -> {
                        it.errorMsg.logE()
                    }
                }
            })
    }

}
