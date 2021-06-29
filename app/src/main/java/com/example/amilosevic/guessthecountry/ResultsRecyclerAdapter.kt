package com.example.amilosevic.guessthecountry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amilosevic.guessthecountry.model.User
import kotlinx.android.synthetic.main.see_results_rv_item.view.*

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
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ResultsViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val imgView = itemView.iv_user_image
        val username = itemView.tv_user_name
        val score = itemView.tv_score
        val date = itemView.tv_date

       fun bind(user: User) {
           username.text = user.username
           score.text = "Score: " + user.score.toString() + "/5"
           date.text = user.date.toString()

           Glide.with(itemView.context)
               .load(user.imageURL)
               .into(imgView)
       }
    }

}