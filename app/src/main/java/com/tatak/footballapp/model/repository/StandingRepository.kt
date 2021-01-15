package com.tatak.footballapp.model.repository

import io.reactivex.Flowable
import com.tatak.footballapp.model.Standings

interface StandingRepository {

    fun getStanding(l: String) : Flowable<Standings>

}