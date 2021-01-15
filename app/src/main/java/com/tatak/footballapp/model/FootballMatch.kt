package com.tatak.footballapp.model

import com.google.gson.annotations.SerializedName

data class FootballMatch (
        @SerializedName("events") var events: List<Event>
)


