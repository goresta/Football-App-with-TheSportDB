package com.tatak.footballapp.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import org.jetbrains.anko.startActivity
import com.tatak.footballapp.R
import com.tatak.footballapp.ui.searchmatches.SearchMatchActivity
import com.tatak.footballapp.adapter.ViewPagerAdapter
import com.tatak.footballapp.ui.lastmatch.LastMatchFragment
import com.tatak.footballapp.ui.upcomingmatch.UpcomingMatchFragment

class  MatchesFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vPager = view.findViewById<ViewPager>(R.id.viewpager)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)
        val adapter = ViewPagerAdapter(childFragmentManager)
        setHasOptionsMenu(true)
        adapter.listFragment(LastMatchFragment(), getString(R.string.last_match))
        adapter.listFragment(UpcomingMatchFragment(), getString(R.string.upcoming_match))
        vPager.adapter = adapter
        tabs.setupWithViewPager(vPager)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)

        val searchView = menu.findItem(R.id.actionSearch)?.actionView as SearchView?

        searchView?.queryHint = getString(R.string.query_search)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                context?.startActivity<SearchMatchActivity>(getString(
                    R.string.query
                ) to query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })


    }

}