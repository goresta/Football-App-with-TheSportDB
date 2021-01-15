package com.tatak.footballapp.ui.upcomingmatch

import com.tatak.footballapp.model.Event
import com.tatak.footballapp.model.League


interface MatchContract {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun showLeague(data:List<League>)
        fun displayFootballMatch(matchList:List<Event>)
    }

    interface Presenter{
        fun getLeagueDetailData(leagueId: String = "4328")
        fun getFootballMatchData(leagueName: String = "4328")
        fun onDestroyPresenter()

    }
}