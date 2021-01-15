package com.tatak.footballapp.ui.standing

import com.tatak.footballapp.model.Standing


interface StandingContract {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun showStanding(l:List<Standing>)
        fun hideSwipeRefresh()
    }

    interface Presenter{
        fun getStandingData(l: String = "4328")
        fun onDestroyPresenter()

    }
}