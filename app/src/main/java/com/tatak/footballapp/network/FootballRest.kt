package com.tatak.footballapp.network

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import com.tatak.footballapp.model.*


interface FootballRest {

    @GET("eventspastleague.php")
    fun getLastmatch(@Query("id") id:String) : Flowable<FootballMatch>

    @GET("eventsnextleague.php")
    fun getUpcomingMatch(@Query("id") id:String) : Flowable<FootballMatch>

    @GET("searchevents.php")
    fun searchMatches(@Query("e") query: String?) : Flowable<SearchedMatches>

    @GET("lookupevent.php")
    fun getEventById(@Query("id") id:String) : Flowable<FootballMatch>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id:String) : Flowable<Teams>

    @GET("searchteams.php")
    fun getTeamBySearch(@Query("t") query: String?) : Flowable<SearchedTeams>

    @GET("lookup_all_teams.php")
    fun getAllTeam(@Query("id") id:String) : Flowable<Teams>

    @GET("lookupleague.php")
    fun getDetailLeague(@Query("id") id:String) : Flowable<Leagues>

    @GET("lookuptable.php")
    fun getStanding(@Query("l") l:String) : Flowable<Standings>

}