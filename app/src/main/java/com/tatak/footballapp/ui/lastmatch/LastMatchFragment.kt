package com.tatak.footballapp.ui.lastmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_last_match.*
import com.tatak.footballapp.*
import com.tatak.footballapp.adapter.LeagueAdapter
import com.tatak.footballapp.adapter.MatchAdapter
import com.tatak.footballapp.extensions.hide
import com.tatak.footballapp.extensions.show
import com.tatak.footballapp.model.Event
import com.tatak.footballapp.model.League
import com.tatak.footballapp.model.repository.LeagueRepositoryImpl
import com.tatak.footballapp.model.repository.MatchRepositoryImpl
import com.tatak.footballapp.network.FootballApiService
import com.tatak.footballapp.network.FootballRest
import com.tatak.footballapp.ui.upcomingmatch.MatchContract
import com.tatak.footballapp.utils.AppSchedulerProvider


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class LastMatchFragment : Fragment(), MatchContract.View {

    lateinit var mPresenter : LastMatchPresenter
    lateinit var leagueName : String

    private var matchLists : MutableList<Event> = mutableListOf()
    private var leagueLists: MutableList<League> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballApiService.getClient()
            .create(FootballRest::class.java)
        val request = MatchRepositoryImpl(service)
        val league = LeagueRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()
        mPresenter = LastMatchPresenter(
            this,
            request,
            league,
            scheduler
        )
        mPresenter.getLeagueDetailData()
        mPresenter.getFootballMatchData()
        val spinnerItems = resources.getStringArray(R.array.leagueArray)
        val spinnerAdapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, spinnerItems) }
        spinnerMatch.adapter = spinnerAdapter

        spinnerMatch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinnerMatch.selectedItem.toString()
                when(leagueName){
                    getString(R.string.english_league) -> mPresenter.getLeagueDetailData(getString(
                        R.string.english_league_id
                    ))
                    getString(R.string.german_league) -> mPresenter.getLeagueDetailData(getString(
                        R.string.german_league_id
                    ))
                    getString(R.string.italian_league) -> mPresenter.getLeagueDetailData(getString(
                        R.string.italian_league_id
                    ))
                    getString(R.string.french_league) -> mPresenter.getLeagueDetailData(getString(
                        R.string.french_league_id
                    ))
                    getString(R.string.spanish_league) -> mPresenter.getLeagueDetailData(getString(
                        R.string.spanish_league_id
                    ))
                    getString(R.string.netherland_league) -> mPresenter.getLeagueDetailData(getString(
                        R.string.netherland_league_id
                    ))
                    else -> mPresenter.getLeagueDetailData()
                }
                when(leagueName){
                    getString(R.string.english_league) -> mPresenter.getFootballMatchData(getString(
                        R.string.english_league_id))
                    getString(R.string.german_league) -> mPresenter.getFootballMatchData(getString(R.string.german_league_id))
                    getString(R.string.italian_league) -> mPresenter.getFootballMatchData(getString(
                        R.string.italian_league_id))
                    getString(R.string.french_league) -> mPresenter.getFootballMatchData(getString(R.string.french_league_id))
                    getString(R.string.spanish_league) -> mPresenter.getFootballMatchData(getString(
                        R.string.spanish_league_id))
                    getString(R.string.netherland_league) -> mPresenter.getFootballMatchData(getString(
                        R.string.netherland_league_id))
                    else -> mPresenter.getFootballMatchData()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun hideLoading() {
        mainProgressBar.hide()
        rvFootballLast.visibility = View.VISIBLE
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvFootballLast.visibility = View.INVISIBLE
    }

    override fun showLeague(data: List<League>) {
        leagueLists.clear()
        leagueLists.addAll(data)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvLeagueLast.layoutManager = layoutManager
        rvLeagueLast.adapter = LeagueAdapter(data, context)
    }

    override fun displayFootballMatch(matchList: List<Event>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvFootballLast.layoutManager = layoutManager
        rvFootballLast.adapter =
            MatchAdapter(matchList, context)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }

}
