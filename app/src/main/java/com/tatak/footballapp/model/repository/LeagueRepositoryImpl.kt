package com.tatak.footballapp.model.repository

import io.reactivex.Flowable
import com.tatak.footballapp.network.FootballRest
import com.tatak.footballapp.model.Leagues

class LeagueRepositoryImpl(private val footballRest: FootballRest):
    LeagueRepository {

    override fun getLeagueData(id: String): Flowable<Leagues> = footballRest.getDetailLeague(id)

}