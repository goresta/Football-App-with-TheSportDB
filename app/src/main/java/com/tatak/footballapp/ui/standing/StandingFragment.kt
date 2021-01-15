package com.tatak.footballapp.ui.standing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_standing.*
import com.tatak.footballapp.R
import com.tatak.footballapp.adapter.StandingAdapter
import com.tatak.footballapp.extensions.hide
import com.tatak.footballapp.extensions.show
import com.tatak.footballapp.model.Standing
import com.tatak.footballapp.model.repository.StandingRepositoryImpl
import com.tatak.footballapp.network.FootballApiService
import com.tatak.footballapp.network.FootballRest
import com.tatak.footballapp.utils.AppSchedulerProvider

class  StandingFragment : Fragment(), StandingContract.View {

    lateinit var mPresenter : StandingPresenter

    lateinit var leagueName: String

    private var standingLists: MutableList<Standing> = mutableListOf()

    override fun hideLoading() {
        mainProgressBar.hide()
        rvStanding.visibility = View.VISIBLE
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvStanding.visibility = View.INVISIBLE
    }

    override fun showStanding(l: List<Standing>) {
        standingLists.clear()
        standingLists.addAll(l)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvStanding.layoutManager = layoutManager
        rvStanding.adapter = StandingAdapter(l, context)
    }

    override fun hideSwipeRefresh() {
        swipe.isRefreshing = false
        mainProgressBar.hide()
        rvStanding.visibility = View.VISIBLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_standing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballApiService.getClient()
            .create(FootballRest::class.java)
        val standing = StandingRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()
        mPresenter = StandingPresenter(
            this,
            standing,
            scheduler
        )
        val spinnerItems = resources.getStringArray(R.array.leagueArray)
        val spinnerAdapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, spinnerItems) }
        spinnerStanding.adapter = spinnerAdapter

        spinnerStanding.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinnerStanding.selectedItem.toString()
                when(leagueName){
                    getString(R.string.english_league) -> mPresenter.getStandingData(getString(
                        R.string.english_league_id
                    ))
                    getString(R.string.german_league) -> mPresenter.getStandingData(getString(
                        R.string.german_league_id
                    ))
                    getString(R.string.italian_league) -> mPresenter.getStandingData(getString(
                        R.string.italian_league_id
                    ))
                    getString(R.string.french_league) -> mPresenter.getStandingData(getString(
                        R.string.french_league_id
                    ))
                    getString(R.string.spanish_league) -> mPresenter.getStandingData(getString(
                        R.string.spanish_league_id
                    ))
                    getString(R.string.netherland_league) -> mPresenter.getStandingData(getString(
                        R.string.netherland_league_id
                    ))
                    else -> mPresenter.getStandingData()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        mPresenter.getStandingData()
        swipe.setOnRefreshListener {
            mPresenter.getStandingData()
            swipe.isRefreshing = false
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }
}