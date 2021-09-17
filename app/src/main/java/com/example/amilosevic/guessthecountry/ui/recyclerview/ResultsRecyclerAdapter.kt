package com.example.amilosevic.guessthecountry.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amilosevic.guessthecountry.R
import com.example.amilosevic.guessthecountry.model.ResultDetails
import kotlinx.android.synthetic.main.see_results_rv_item.view.*

class ResultsRecyclerAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ResultsViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.see_results_rv_item, parent, false)
            )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ResultsViewHolder -> {
                holder.bind(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getResultAt(position: Int) : String {
        return list[position]
    }

    inner class ResultsViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val imgView = itemView.iv_user_image
        val score = itemView.tv_score
        val date = itemView.tv_date

       fun bind(resultDetails: String) {

       }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION)
                listener.onItemClick(position)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}