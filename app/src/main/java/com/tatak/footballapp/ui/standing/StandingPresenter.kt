package com.tatak.footballapp.ui.standing

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import com.tatak.footballapp.model.Standings
import com.tatak.footballapp.model.repository.StandingRepositoryImpl
import com.tatak.footballapp.utils.SchedulerProvider
import java.util.*

class StandingPresenter(val mView :  StandingContract.View,
                        private val standingRepositoryImpl: StandingRepositoryImpl,
                        private val scheduler: SchedulerProvider
) : StandingContract.Presenter {


    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }

    private val compositeDisposable = CompositeDisposable()

    override fun getStandingData(l: String) {
        mView.showLoading()
        compositeDisposable.add(standingRepositoryImpl.getStanding(l)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribeWith(object : ResourceSubscriber<Standings>(){
                override fun onComplete() {
                    mView.hideLoading()
                }

                override fun onNext(t: Standings) {
                    mView.showStanding(t.standings)
                }

                override fun onError(e: Throwable) {
                    mView.hideLoading()
                    mView.showStanding(Collections.emptyList())
                }

            })
        )
    }

}