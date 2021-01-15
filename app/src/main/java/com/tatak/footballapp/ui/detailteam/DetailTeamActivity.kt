package com.tatak.footballapp.ui.detailteam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.toast
import com.tatak.footballapp.R
import com.tatak.footballapp.model.Team
import com.tatak.footballapp.model.db.FavoriteTeam
import com.tatak.footballapp.model.repository.LocalRepositoryImpl

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailTeamActivity : AppCompatActivity(), TeamDetailContract.View {

    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    override fun setFavoriteState(favList: List<FavoriteTeam>) {
        if(favList.isNotEmpty()) isFavorite = true
    }

    lateinit var team: Team
    private lateinit var mPresenter: TeamDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        team = intent.getParcelableExtra("team")

        val bundle = Bundle()
        bundle.putParcelable("teams", team)
        supportActionBar?.title = team.strTeam

        initView(team)

        val localRepo = LocalRepositoryImpl(applicationContext)
        mPresenter = TeamDetailPresenter(this, localRepo)
        team.idTeam?.let { mPresenter.checkTeam(it) }

    }

    private fun initView(teamInfo: Team?){
        Glide.with(this)
            .load(teamInfo?.strTeamBadge)
            .apply(RequestOptions().placeholder(R.drawable.ic_soccer))
            .into(imgBadge)

        teamName.text = teamInfo?.strTeam
        tvManager.text = teamInfo?.strManager
        tvStadium.text = teamInfo?.strStadium
        teamOverview.text = teamInfo?.strDescriptionEN
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fav, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favorite -> {
                isFavorite = if (!isFavorite){
                    team.idTeam?.let { team.strTeamBadge?.let { it1 ->
                        mPresenter.insertTeam(it,
                            it1
                        )
                    } }
                    toast("Team added to favorite")
                    !isFavorite
                }else{
                    team.idTeam?.let { mPresenter.deleteTeam(it) }
                    toast("Team removed from favorite")
                    !isFavorite
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
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
    }

}
