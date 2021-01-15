package com.tatak.footballapp.model.repository


import io.reactivex.Flowable
import io.reactivex.Observable
import com.tatak.footballapp.network.FootballRest
import com.tatak.footballapp.model.Teams


class TeamRepositoryImpl(private val footballRest: FootballRest) :
    TeamRepository {

    override fun getAllTeam(id: String) = footballRest.getAllTeam(id)
    override fun getTeams(id: String): Flowable<Teams> = footballRest.getAllTeam(id)
    override fun getTeamsDetail(id: String): Flowable<Teams> = footballRest.getTeam(id)
    override fun searchTeams(query: String?) = footballRest.getTeamBySearch(query)

}