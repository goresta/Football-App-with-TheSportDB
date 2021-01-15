package com.tatak.footballapp.ui.searchmatches


import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import com.tatak.footballapp.model.repository.MatchRepositoryImpl
import com.tatak.footballapp.utils.SchedulerProvider
import com.tatak.footballapp.model.SearchedMatches
import java.util.*

class SearchMatchPresenter(val mView: SearchMatchContract.View,
                           private val matchRepositoryImpl: MatchRepositoryImpl,
                           private val schedulerProvider: SchedulerProvider
): SearchMatchContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun searchMatch(query: String?) {
        mView.showLoading()
        compositeDisposable.add(matchRepositoryImpl.searchMatches(query)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(object: ResourceSubscriber<SearchedMatches>(){
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: SearchedMatches?) {
                        t?.events?.filter { it.strSport == "Soccer" }?.let { mView.displayMatch(it) }
                    }

                    override fun onError(t: Throwable?) {
                        mView.displayMatch(Collections.emptyList())
                        mView.hideLoading()
                    }

                })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }


}