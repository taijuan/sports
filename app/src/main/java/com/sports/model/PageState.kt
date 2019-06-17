package com.sports.model

enum class PageState {
    Init,
    FirstLoadMore,
    FirstLoadMoreNoData,
    FirstEmpty,
    FirstError,
    RefreshLoadMore,
    RefreshLoadMoreNoData,
    RefreshEmpty,
    RefreshError,
    LoadError,
    LoadMore,
    LoadMoreNoData,
}