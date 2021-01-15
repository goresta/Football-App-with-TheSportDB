package com.tatak.footballapp.model

import com.google.gson.annotations.SerializedName

class Standings (@SerializedName("table")
                 var standings: List<Standing>)