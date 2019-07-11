package com.sports

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.sports.adapter.NewsAdapter
import com.sports.api.*
import com.sports.base.BaseActivity
import com.sports.model.*
import com.sports.utils.EncryptUtils
import com.sports.utils.logE
import com.sports.viewmodel.NewsDataViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_refresh.*
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
        newsDataViewModel.state.observe(this, Observer {
            it.logE()
            when (it) {
                PageState.FirstLoadMore -> {
                    emptyView.hide()
                    smartRefreshLayout.visibility = View.VISIBLE
                    smartRefreshLayout.finishRefresh()
                    smartRefreshLayout.finishLoadMore()
                }
                PageState.FirstLoadMoreNoData -> {
                    emptyView.hide()
                    smartRefreshLayout.visibility = View.VISIBLE
                    smartRefreshLayout.finishRefresh()
                    smartRefreshLayout.finishLoadMoreWithNoMoreData()
                }
                PageState.FirstEmpty -> {
                    emptyView.show(false, "数据为空", null, "点击重试") {
                        newsDataViewModel.firstLoad(this, "asia", 1)
                        emptyView.show(true)
                    }
                }
                PageState.FirstError -> {
                    emptyView.show(false, "请求失败", null, "点击重试") {
                        newsDataViewModel.firstLoad(this, "asia", 1)
                        emptyView.show(true)
                    }
                }
                PageState.RefreshLoadMore -> {
                    emptyView.hide()
                    smartRefreshLayout.visibility = View.VISIBLE
                    smartRefreshLayout.finishRefresh()
                    smartRefreshLayout.finishLoadMore()
                }
                PageState.RefreshLoadMoreNoData -> {
                    emptyView.hide()
                    smartRefreshLayout.visibility = View.VISIBLE
                    smartRefreshLayout.finishRefresh()
                    smartRefreshLayout.finishLoadMoreWithNoMoreData()
                }
                PageState.RefreshEmpty -> {
                    emptyView.hide()
                    emptyView.show(false, "数据为空", null, "点击重试") {
                        newsDataViewModel.firstLoad(this, "asia", 1)
                        emptyView.show(true)
                    }
                    smartRefreshLayout.visibility = View.VISIBLE
                    smartRefreshLayout.finishRefresh()
                    smartRefreshLayout.finishLoadMoreWithNoMoreData()
                }
                PageState.RefreshError -> {
                    emptyView.hide()
                    smartRefreshLayout.visibility = View.VISIBLE
                    smartRefreshLayout.finishRefresh(false)
                }
                PageState.LoadMore -> {
                    smartRefreshLayout.finishLoadMore()
                }
                PageState.LoadMoreNoData -> {
                    smartRefreshLayout.finishLoadMoreWithNoMoreData()
                }
                PageState.LoadError -> {
                    smartRefreshLayout.finishLoadMore(false)
                }
                else -> {
                }
            }
        })
        newsDataViewModel.firstData.observe(this, Observer {
            smartRefreshLayout.visibility = View.VISIBLE
            adapter.refresh(it)
        })
        newsDataViewModel.refreshData.observe(this, Observer {
            smartRefreshLayout.visibility = View.VISIBLE
            adapter.refresh(it)
        })
        newsDataViewModel.loadMoreData.observe(this, Observer {
            adapter.loadMore(it)
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
        testApi()
        testRegApi()


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

    private fun testApi() {
        val phone = "15528363539"
        testService.testApi(phone).observe(this, Observer {
            when (it) {
                is Success<BaseRes<String>> -> {
                    it.logE()
                }
                is Error<BaseRes<String>> -> {
                    it.errorMsg.logE()
                }
            }
        })
    }

    private fun testRegApi() {
        val req = RegReq("15528363539", "123456", "12345678", "12")
        testService.testRegApi(req).observe(this, Observer {
            when (it) {
                is Success<BaseRes<String>> -> {
                    it.logE()
                }
                is Error<BaseRes<String>> -> {
                    it.errorMsg.logE()
                }
            }
        })
    }
}
