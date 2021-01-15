package com.tatak.footballapp.ui.favoriteteam

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import com.tatak.footballapp.model.Team
import com.tatak.footballapp.model.Teams
import com.tatak.footballapp.model.repository.LocalRepositoryImpl
import com.tatak.footballapp.model.repository.TeamRepositoryImpl
import com.tatak.footballapp.utils.SchedulerProvider
import java.util.*

class FavoriteTeamPresenter(val mView: FavoriteTeamContract.View,
                            private val localRepositoryImpl: LocalRepositoryImpl,
                            private val teamRepositoryImpl: TeamRepositoryImpl,
                            val scheduler: SchedulerProvider
): FavoriteTeamContract.Presenter {


    private val compositeDisposable = CompositeDisposable()

    override fun getTeamData() {
        mView.showLoading()
        val teamList = localRepositoryImpl.getTeamFromDb()
        val teamLists: MutableList<Team> = mutableListOf()
        for (fav in teamList){
            compositeDisposable.add(teamRepositoryImpl.getTeamsDetail(fav.idTeam)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object: ResourceSubscriber<Teams>(){
                    override fun onComplete() {
                        mView.hideLoading()
                        mView.hideSwipeRefresh()
                    }

                    override fun onNext(t: Teams) {
                        teamLists.add(t.teams[0])
                        mView.displayTeams(teamLists)
                    }

                    override fun onError(t: Throwable?) {
                        mView.hideLoading()
                        mView.hideSwipeRefresh()
                        mView.displayTeams(Collections.emptyList())
                    }

                }))
        }

        if(teamList.isEmpty()){
            mView.hideLoading()
            mView.hideSwipeRefresh()
            mView.displayTeams(teamLists)
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}