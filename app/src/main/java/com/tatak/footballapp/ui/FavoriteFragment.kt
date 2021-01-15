package com.tatak.footballapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.tatak.footballapp.R
import com.tatak.footballapp.adapter.ViewPagerAdapter
import com.tatak.footballapp.ui.favoritematch.FavoriteMatchFragment
import com.tatak.footballapp.ui.favoriteteam.FavoriteTeamFragment

class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vPager = view.findViewById<ViewPager>(R.id.viewpager)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.listFragment(FavoriteMatchFragment(), "Favorite Match")
        adapter.listFragment(FavoriteTeamFragment(), "Favorite Team")
        vPager.adapter = adapter
        tabs.setupWithViewPager(vPager)
    }


}
