package com.tatak.footballapp.model.repository


import io.reactivex.Flowable
import io.reactivex.Observable
import com.tatak.footballapp.model.SearchedTeams
import com.tatak.footballapp.model.Teams

interface TeamRepository {

    fun getTeams(id : String = "0") : Flowable<Teams>

    fun getTeamsDetail(id : String = "0") : Flowable<Teams>

    fun getAllTeam(id: String) : Flowable<Teams>

    fun searchTeams(query: String?) : Flowable<SearchedTeams>
}