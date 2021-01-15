package com.tatak.footballapp.ui.searchteam

import com.tatak.footballapp.model.Team

interface SearchTeamContract {

    interface View{
        fun showLoading()
        fun hideLoading()
        fun displayTeam(teamList: List<Team>)
    }
    interface Presenter{
        fun searchTeam(query: String?)
        fun onDestroy()
    }
}