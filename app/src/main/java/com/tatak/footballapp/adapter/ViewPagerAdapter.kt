package com.tatak.footballapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


@Suppress("DEPRECATION")
class ViewPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager!!){

    private var fragmentList = arrayListOf<Fragment>()
    private var titleList = arrayListOf<String>()

    fun listFragment(fragment: Fragment,title: String){
        fragmentList.add(fragment)
        titleList.add(title)
    }
    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size

    override fun getPageTitle(position: Int) = titleList[position]
}