package com.tatak.footballapp.model.repository

import io.reactivex.Flowable
import com.tatak.footballapp.model.FootballMatch
import com.tatak.footballapp.model.SearchedMatches

interface MatchRepository {

    fun getFootballMatch(id : String) : Flowable<FootballMatch>

    fun getUpcomingMatch(id : String) : Flowable<FootballMatch>

    fun getEventById(id: String) : Flowable<FootballMatch>

    fun searchMatches(query: String?) : Flowable<SearchedMatches>
}