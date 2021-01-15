package com.tatak.footballapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.league_item.view.*
import com.tatak.footballapp.R
import com.tatak.footballapp.model.League


class LeagueAdapter(private val leagueList: List<League>, val context: Context?) : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(LayoutInflater.from(context).inflate(R.layout.league_item, parent, false))
    }

    override fun getItemCount(): Int = leagueList.size


    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(leagueList[position])
    }

    @Suppress("DEPRECATION")
    inner class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(league: League) {


            context?.let {
                Glide.with(it)
                    .load(league.strBadge)
                    .apply(RequestOptions().placeholder(R.drawable.ic_soccer))
                    .into(itemView.ivLeague)
            }


            itemView.tvLeagueName.text = league.strLeague
            itemView.tvLeagueDesc.text = league.strDescriptionEN

        }
    }

}