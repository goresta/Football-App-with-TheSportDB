package com.tatak.footballapp.model

import com.google.gson.annotations.SerializedName

data class SearchedTeams(
    @SerializedName("teams")
    var teams: List<Team>)