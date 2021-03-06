package com.app.reactortest.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.app.reactortest.GiphyObject
import com.app.reactortest.R
import com.bumptech.glide.Glide

class SearchResultAdapter(list: List<GiphyObject>) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {
    private var _list: MutableList<GiphyObject> = ArrayList()

    init {
        _list.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_giphy_list, parent, false))

    override fun getItemCount(): Int = _list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .asGif()
            .placeholder(R.drawable.im_no_image)
            .placeholder(R.drawable.im_no_image)
            .load(_list[position].images.imagePreview.url)
            .into(holder.imgGif)


        holder.tvTitle.text = _list[position].title
    }

    fun updateData(list: List<GiphyObject>, isPagination: Boolean) {
        if (!isPagination) {
            _list.clear()
            _list.addAll(list)
            notifyDataSetChanged()
        } else {
            val insertedPosition = _list.count() - 1
            _list.addAll(list)
            notifyItemRangeInserted(insertedPosition, list.count())
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgGif: ImageView = itemView.findViewById(R.id.item_giphy_list_img_gif)
        val tvTitle: TextView = itemView.findViewById(R.id.item_giphy_list_tv_title)
    }
}
