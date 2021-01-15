package com.tatak.footballapp.ui.detailteam

import com.tatak.footballapp.model.repository.LocalRepositoryImpl

class TeamDetailPresenter(val mView: TeamDetailContract.View,
                          private val localRepositoryImpl: LocalRepositoryImpl
): TeamDetailContract.Presenter {

    override fun deleteTeam(id: String) {
        localRepositoryImpl.deleteTeamData(id)
    }

    override fun checkTeam(id: String) {
        mView.setFavoriteState(localRepositoryImpl.checkFavTeam(id))
    }

    override fun insertTeam(id: String, imgUrl: String) {
        localRepositoryImpl.insertTeamData(id, imgUrl)
    }

}