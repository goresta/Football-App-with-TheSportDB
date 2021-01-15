package com.tatak.footballapp.ui.searchmatches

import com.tatak.footballapp.model.Event

interface SearchMatchContract {

    interface View{
        fun showLoading()
        fun hideLoading()
        fun displayMatch(matchList: List<Event>)
    }
    interface Presenter{
        fun searchMatch(query: String?)
        fun onDestroy()
    }
}