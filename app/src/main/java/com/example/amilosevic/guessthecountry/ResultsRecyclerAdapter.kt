package com.example.amilosevic.guessthecountry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amilosevic.guessthecountry.model.User

class ResultsRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ResultsViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.see_results_rv_item, parent, false)
            )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ResultsViewHolder -> {

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ResultsViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {


    }

}