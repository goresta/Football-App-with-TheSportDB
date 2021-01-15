package com.tatak.footballapp.ui.searchteam

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import com.tatak.footballapp.model.SearchedTeams
import com.tatak.footballapp.model.repository.TeamRepositoryImpl
import com.tatak.footballapp.utils.SchedulerProvider
import java.util.*

class SearchTeamPresenter(val mView: SearchTeamContract.View,
                          private val teamRepositoryImpl: TeamRepositoryImpl,
                          private val schedulerProvider: SchedulerProvider
): SearchTeamContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun searchTeam(query: String?) {
        mView.showLoading()
        compositeDisposable.add(teamRepositoryImpl.searchTeams(query)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribeWith(object: ResourceSubscriber<SearchedTeams>(){
                override fun onComplete() {
                    mView.hideLoading()
                }

                override fun onNext(t: SearchedTeams?) {
                    t?.teams?.filter { it.strSport == "Soccer" }?.let { mView.displayTeam(it) }
                }

                override fun onError(t: Throwable?) {
                    mView.displayTeam(Collections.emptyList())
                    mView.hideLoading()
                }

            })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }


}