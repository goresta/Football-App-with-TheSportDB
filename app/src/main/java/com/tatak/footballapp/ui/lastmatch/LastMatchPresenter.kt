package com.tatak.footballapp.ui.lastmatch


import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import com.tatak.footballapp.model.FootballMatch
import com.tatak.footballapp.model.Leagues
import com.tatak.footballapp.model.repository.LeagueRepositoryImpl
import com.tatak.footballapp.model.repository.MatchRepositoryImpl
import com.tatak.footballapp.ui.upcomingmatch.MatchContract
import com.tatak.footballapp.utils.SchedulerProvider
import java.util.*


class LastMatchPresenter(val mView : MatchContract.View,
                         private val matchRepositoryImpl: MatchRepositoryImpl,
                         private val leagueRepositoryImpl: LeagueRepositoryImpl,
                         private val scheduler: SchedulerProvider
) : MatchContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getFootballMatchData(leagueName: String) {
        mView.showLoading()
        compositeDisposable.add(matchRepositoryImpl.getFootballMatch(leagueName)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object : ResourceSubscriber<FootballMatch>(){
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: FootballMatch) {
                        mView.displayFootballMatch(t.events)
                    }

                    override fun onError(e: Throwable) {
                        mView.hideLoading()
                        mView.displayFootballMatch(Collections.emptyList())
                    }

                })
        )
    }

    override fun getLeagueDetailData(leagueId: String) {
        mView.showLoading()
        compositeDisposable.add(leagueRepositoryImpl.getLeagueData(leagueId)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribeWith(object : ResourceSubscriber<Leagues>(){
                override fun onComplete() {
                    mView.hideLoading()
                }

                override fun onNext(t: Leagues) {
                    mView.showLeague(t.leagues)
                }

                override fun onError(e: Throwable) {
                    mView.hideLoading()
                    mView.showLeague(Collections.emptyList())
                }

            })
        )
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}