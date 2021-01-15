package com.tatak.footballapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_match_item.view.*
import org.jetbrains.anko.startActivity
import com.tatak.footballapp.utils.DateHelper
import com.tatak.footballapp.R
import com.tatak.footballapp.model.Event
import com.tatak.footballapp.ui.detailmatch.DetailMatchActivity


class MatchAdapter(private val eventList: List<Event>, val context: Context?) : RecyclerView.Adapter<MatchAdapter.ClubViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        return ClubViewHolder(LayoutInflater.from(context).inflate(R.layout.main_match_item, parent, false))
    }

    override fun getItemCount(): Int = eventList.size


    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    @Suppress("DEPRECATION")
    inner class ClubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event) {

            if (event.intHomeScore == null) {
                itemView.dateScheduleTv.setTextColor(itemView.context.resources.getColor(android.R.color.darker_gray))
            }
            itemView.dateScheduleTv.text = event.dateEvent?.let {
                DateHelper.formatDateToMatch(
                    it
                )
            }
            itemView.homeNameTv.text = event.strHomeTeam
            itemView.homeScoreTv.text = event.intHomeScore
            itemView.awayNameTv.text = event.strAwayTeam
            itemView.awayScoreTv.text = event.intAwayScore

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailMatchActivity>("match" to event)
            }
        }
    }

}