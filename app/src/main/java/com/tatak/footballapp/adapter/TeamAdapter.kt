package com.tatak.footballapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tatak.footballapp.R
import com.tatak.footballapp.model.Team
import com.tatak.footballapp.ui.detailteam.DetailTeamActivity
import kotlinx.android.synthetic.main.team_item.view.*
import org.jetbrains.anko.startActivity

class TeamAdapter(private val teamList: List<Team>, val context: Context?): RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.team_item, parent, false))
    }

    override fun getItemCount() = teamList.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teamList[position])
    }


    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(team: Team){
            itemView.tvTeam.text = team.strTeam
            Glide.with(itemView.context)
                .load(team.strTeamBadge)
                .apply(RequestOptions().placeholder(R.drawable.ic_soccer))
                .into(itemView.ivTeam)

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailTeamActivity>("team" to team)
            }
        }

    }
}