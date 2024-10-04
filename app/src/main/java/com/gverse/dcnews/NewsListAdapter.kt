package com.gverse.dcnews

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gverse.dcnews.NewsDetailActivity.Companion.EXTRA_NEWS

class NewsListAdapter(private val newsList: ArrayList<News>) : RecyclerView.Adapter<NewsListAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = newsList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, description, date, thumbnail) = newsList[position]
        holder.imgPhoto.setImageResource(thumbnail)
        holder.tvTitle.text = title
        holder.tvDate.text = date
        holder.tvDescription.text = description
        holder.tvSource.text = "Liputan6"
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, NewsDetailActivity::class.java)
            intent.putExtra(EXTRA_NEWS, newsList[position])
            it.context.startActivity(intent)
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.news_thumbnail)
        val tvTitle: TextView = itemView.findViewById(R.id.news_item_title)
        val tvDescription: TextView = itemView.findViewById(R.id.news_item_description)
        val tvDate: TextView = itemView.findViewById(R.id.news_item_date)
        val tvSource: TextView = itemView.findViewById(R.id.news_item_category)
    }
}