package com.tatak.footballapp.ui.favoritematch

import com.tatak.footballapp.model.Event

interface FavoriteMatchContract {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun displayFootballMatch(matchList:List<Event>)
        fun hideSwipeRefresh()
    }

    interface Presenter{
        fun getFootballMatchData()
        fun onDestroyPresenter()
    }
}