package com.tatak.footballapp.model.repository

import io.reactivex.Flowable
import com.tatak.footballapp.model.Leagues

interface LeagueRepository {

    fun getLeagueData(id: String) : Flowable<Leagues>

}