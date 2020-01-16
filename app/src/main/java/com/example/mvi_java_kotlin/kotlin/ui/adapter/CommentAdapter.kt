package com.example.mvi_java_kotlin.kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvi_java_kotlin.R
import com.example.mvi_java_kotlin.kotlin.data.Comment

class CommentAdapter(var comments: List<Comment> = listOf()) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.comment_item, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    inner class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val comment: TextView = view.findViewById(R.id.comment)

        fun bind(comment: Comment) {
            this.comment.text = comment.name
        }
    }
}