package com.sports.api

import androidx.lifecycle.LiveData
import com.sports.model.*

class MockSportService :SportService {
    override fun getUser(code: String, curPage: Int, type: Int): LiveData<SuccessError<BaseResObj<PageData<News>>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserToken(
        key: String,
        time: String,
        roundNum: String,
        sha1: String,
        userId: String
    ): LiveData<SuccessError<YongYunUser>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createWYUser(
        key: String,
        time: String,
        roundNum: String,
        sha1: String,
        userId: String
    ): LiveData<SuccessError<WangYiBaseRes<WangYiUser>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createChatRoom(
        key: String,
        time: String,
        roundNum: String,
        sha1: String,
        chatRoomId: String,
        name: String
    ): LiveData<SuccessError<WangYiBaseRes<WangYiUser>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}