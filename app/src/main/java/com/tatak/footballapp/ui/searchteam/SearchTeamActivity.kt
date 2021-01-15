package com.tatak.footballapp.ui.searchteam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_search_team.*
import com.tatak.footballapp.R
import com.tatak.footballapp.adapter.TeamAdapter
import com.tatak.footballapp.extensions.hide
import com.tatak.footballapp.extensions.show
import com.tatak.footballapp.model.Team
import com.tatak.footballapp.model.repository.TeamRepositoryImpl
import com.tatak.footballapp.network.FootballApiService
import com.tatak.footballapp.network.FootballRest
import com.tatak.footballapp.utils.AppSchedulerProvider

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SearchTeamActivity : AppCompatActivity(),
    SearchTeamContract.View {

    private var teamLists : MutableList<Team> = mutableListOf()
    lateinit var mPresenter: SearchTeamContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        val query = intent.getStringExtra("query")
        Log.v("test", query)
        val service = FootballApiService.getClient()
            .create(FootballRest::class.java)
        val request = TeamRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()
        mPresenter = SearchTeamPresenter(
            this,
            request,
            scheduler
        )
        mPresenter.searchTeam(query)

    }

    override fun showLoading() {
        mainProgressBar.show()
        rvTeam.hide()
    }

    override fun hideLoading() {
        mainProgressBar.hide()
        rvTeam.show()
    }

    override fun displayTeam(teamList: List<Team>) {
        teamLists.clear()
        teamLists.addAll(teamList)
        val layoutManager = GridLayoutManager(applicationContext, 3)
        rvTeam.layoutManager = layoutManager
        rvTeam.adapter = TeamAdapter(teamLists, applicationContext)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search Matches"

        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mPresenter.searchTeam(newText)
                return false
            }
        })

        return true

    }
}
