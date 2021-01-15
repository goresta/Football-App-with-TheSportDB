package com.tatak.footballapp.ui.listteam

import com.tatak.footballapp.model.Team

interface TeamContract {
    interface View{
        fun displayTeams(teamList: List<Team>)
        fun hideLoading()
        fun showLoading()

    }
    interface Presenter{
        fun getTeamData(leagueName: String = "4328")
        fun onDestroy()
    }
}