package com.example.queenapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.queenapi.R
import com.example.queenapi.model.Queen

class QueenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var queenName = itemView.findViewById<TextView>(R.id.txt_name)
    var queenClass = itemView.findViewById<TextView>(R.id.txt_class)
    var queenImgae = itemView.findViewById<ImageView>(R.id.queen_image)

    fun bind(queen: Queen, clickListener: QueenAdapter.ClickListener) {
        itemView.setOnClickListener {
            clickListener.onClick(queen)
        }
    }
}

class QueenAdapter(
    val queenList: List<Queen>,
    val context: Context,
    val onClickListener: ClickListener
) : RecyclerView.Adapter<QueenViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueenViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.queen_selection, parent, false)
        return QueenViewHolder(view)
    }

    override fun getItemCount(): Int {
        return queenList.size
    }

    override fun onBindViewHolder(holder: QueenViewHolder, position: Int) {

        holder.queenName.text = queenList[position].name
        holder.queenClass.text = queenList[position].jsonMemberClass

        Glide.with(context)
            .load(queenList[position].imgUrl)
            .into(holder.queenImgae)

        holder.bind(queenList[position],onClickListener)
    }

    interface ClickListener {
        fun onClick(queen: Queen)
    }
}
