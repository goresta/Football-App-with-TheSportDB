package com.tatak.footballapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.bottom_nav_view.*
import com.tatak.footballapp.R
import com.tatak.footballapp.ui.listteam.TeamFragment
import com.tatak.footballapp.ui.standing.StandingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.leagueMatch -> {
                    loadMatch(savedInstanceState)
                }
                R.id.standingNav -> {
                    loadStanding(savedInstanceState)
                }
                R.id.teamNav -> {
                    loadTeam(savedInstanceState)
                }
                R.id.favNav -> {
                    loadFavorites(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.leagueMatch

    }

    private fun loadMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.main_container,
                    MatchesFragment(), MatchesFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadStanding(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.main_container,
                    StandingFragment(), StandingFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadTeam(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.main_container,
                    TeamFragment(), TeamFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavorites(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }

}
