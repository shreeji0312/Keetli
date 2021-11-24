package com.shreeji.keetli.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shreeji.keetli.R
import com.shreeji.keetli.model.Post
import com.squareup.picasso.Picasso


class PostAdapter (private val context: Context, private val postList: ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val postImage: ImageView = view.findViewById(R.id.postImage)
        val userName: TextView = view.findViewById(R.id.userName)
        val description: TextView = view.findViewById(R.id.readDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postList[position]
        holder.userName.text = post.userName
        holder.description.text = post.description
        val imageURI = post.profileImage.toString()
        Picasso.get().load(imageURI).into(holder.postImage)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}