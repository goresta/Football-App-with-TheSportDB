package com.tatak.footballapp.ui.detailteam

import com.tatak.footballapp.model.db.FavoriteTeam

interface TeamDetailContract {

    interface View{
        fun setFavoriteState(favList:List<FavoriteTeam>)
    }

    interface Presenter{
        fun deleteTeam(id:String)
        fun checkTeam(id:String)
        fun insertTeam(id: String, imgUrl: String)
    }
}