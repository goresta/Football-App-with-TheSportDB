package com.tatak.footballapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.standing_item.view.*
import com.tatak.footballapp.R
import com.tatak.footballapp.model.Standing

class StandingAdapter(private val standingList: List<Standing>, val context: Context?) : RecyclerView.Adapter<StandingAdapter.StandingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingViewHolder {
        return StandingViewHolder(LayoutInflater.from(context).inflate(R.layout.standing_item, parent, false))
    }

    override fun getItemCount(): Int = standingList.size


    override fun onBindViewHolder(holder: StandingViewHolder, position: Int) {
        holder.bind(standingList[position], position)
    }

    @Suppress("DEPRECATION")
    inner class StandingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(standing: Standing, position: Int) {

            itemView.tvPosition.text = (position + 1).toString()
            itemView.tvTeamName.text = standing.name
            itemView.tvPlayed.text = standing.played
            itemView.tvWin.text = standing.win
            itemView.tvDraw.text = standing.draw
            itemView.tvLose.text = standing.loss
            itemView.tvGFGA.text = "${standing.goalsFor} - ${standing.goalsAgainst}"
            itemView.tvGD.text = standing.goalsDeference
            itemView.tvPts.text = standing.total

        }
    }

}