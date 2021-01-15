package com.tatak.footballapp.model.repository

import io.reactivex.Flowable
import com.tatak.footballapp.network.FootballRest
import com.tatak.footballapp.model.FootballMatch

class MatchRepositoryImpl(private val footballRest: FootballRest) :
    MatchRepository {

    override fun searchMatches(query: String?) = footballRest.searchMatches(query)

    override fun getEventById(id: String): Flowable<FootballMatch> = footballRest.getEventById(id)

    override fun getUpcomingMatch(id: String): Flowable<FootballMatch> = footballRest.getUpcomingMatch(id)

    override fun getFootballMatch(id: String): Flowable<FootballMatch> = footballRest.getLastmatch(id)
}