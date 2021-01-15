package com.tatak.footballapp.ui.detailmatch


import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import com.tatak.footballapp.*
import com.tatak.footballapp.model.Event
import com.tatak.footballapp.model.db.FavoriteMatch
import com.tatak.footballapp.model.db.database
import com.tatak.footballapp.model.repository.LocalRepositoryImpl
import com.tatak.footballapp.model.repository.TeamRepositoryImpl
import com.tatak.footballapp.network.FootballApiService
import com.tatak.footballapp.network.FootballRest
import com.tatak.footballapp.utils.AppSchedulerProvider
import com.tatak.footballapp.utils.DateHelper
import com.tatak.footballapp.utils.loadImage


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailMatchActivity : AppCompatActivity(),
    DetailMatchContract.View {

    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    lateinit var event: Event
    private lateinit var eventId: String

    override fun showBadgeImageHome(string: String) {
        loadImage(this, string, homeImg)
    }

    override fun showBadgeImageAway(string: String) {
        loadImage(this, string, awayImg)
    }

    private lateinit var mMatchPresenter: DetailMatchPresenter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        val service = FootballApiService.getClient()
            .create(FootballRest::class.java)
        val request = TeamRepositoryImpl(service)
        val localRepo = LocalRepositoryImpl(applicationContext)
        val scheduler = AppSchedulerProvider()
        mMatchPresenter = DetailMatchPresenter(this, request, localRepo, scheduler)

        event = intent.getParcelableExtra("match")
        eventId = event.idEvent

        setFavoriteCheck()

        mMatchPresenter.getBadge(event.idHomeTeam, 1)
        mMatchPresenter.getBadge(event.idAwayTeam, 2)
        initData(event)
        supportActionBar?.title = event.strEvent
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initData(event: Event) {
        if (event.intHomeScore == null) {
            dateScheduleTv.setTextColor(applicationContext.getColor(android.R.color.white))
        }

        dateScheduleTv.text = event.dateEvent?.let {
            DateHelper.formatDateToMatch(
                it
            )
        }
        homeNameTv.text = event.strHomeTeam
        homeScoreTv.text = event.intHomeScore
        awayNameTv.text = event.strAwayTeam
        awayScoreTv.text = event.intAwayScore

        homeScorerTv.text = event.strHomeGoalDetails
        awayScorerTv.text = event.strAwayGoalDetails

        gkHomeTv.text = event.strHomeLineupGoalkeeper
        gkAwayTv.text = event.strAwayLineupGoalkeeper

        defHomeTv.text = event.strHomeLineupDefense
        defAwayTv.text = event.strAwayLineupDefense

        midHomeTv.text = event.strHomeLineupMidfield
        midAwayTv.text = event.strAwayLineupMidfield

        forHomeTv.text = event.strHomeLineupForward
        forAwayTv.text = event.strAwayLineupForward

        subHomeTv.text = event.strHomeLineupSubstitutes
        subAwayTv.text = event.strAwayLineupSubstitutes

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fav, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favorite -> {
                if (!isFavorite) {
                    mMatchPresenter.insertMatch(
                        event.idEvent, event.idHomeTeam, event.idAwayTeam)
                    toast("Match added to favorite")
                    isFavorite = !isFavorite
                } else if(isFavorite){
                    mMatchPresenter.deleteMatch(event.idEvent)
                    toast("Match removed favorite")
                    isFavorite = !isFavorite
                }
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        else if(!isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
    }

    override fun setFavoriteState(favList: List<FavoriteMatch>) {
        if (favList.isNotEmpty()) isFavorite = true
        setFavorite()
    }

    private fun setFavoriteCheck(){
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE)
                .whereArgs(
                    "(EVENT_ID = {id})",
                    "id" to eventId
                )
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (favorite.isNotEmpty()) isFavorite = true
            setFavorite()
            println("isFavorite: $isFavorite Favorite: $favorite")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mMatchPresenter.onDestroyPresenter()
    }

}