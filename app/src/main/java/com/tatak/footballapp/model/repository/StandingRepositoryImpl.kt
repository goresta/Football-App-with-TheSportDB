package com.tatak.footballapp.model.repository

import io.reactivex.Flowable
import com.tatak.footballapp.model.Standings
import com.tatak.footballapp.network.FootballRest

class StandingRepositoryImpl(private val footballRest: FootballRest):
    StandingRepository {

    override fun getStanding(l: String): Flowable<Standings> = footballRest.getStanding(l)

}