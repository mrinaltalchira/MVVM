package com.Mvvm.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Mvvm.auth.authActivity.databinding.HomePostLayoutFileBinding
import com.Mvvm.model.posts.Post
import com.Mvvm.model.posts.Userpost
import com.bumptech.glide.Glide
import org.ocpsoft.prettytime.PrettyTime
import java.util.*
import android.text.format.DateUtils
import android.widget.Toast
import java.text.ParseException
import java.text.SimpleDateFormat


class Post_Adapter() : RecyclerView.Adapter<Post_Adapter.ViewHolder>() {
    lateinit var post: MutableList<Post>
    lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun setPost(post: List<Post>, context: Context) {
        this.post = post as MutableList<Post>
        this.context = context
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: HomePostLayoutFileBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomePostLayoutFileBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var place = post[position]
        holder.binding.tvUserName.setText(place.owner.username)
        holder.binding.tvTitleUserName.setText(place.owner.username)
        holder.binding.tvTitle.setText(place.caption)


        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"))
        try {
            val time: Long = sdf.parse(place.createdAT).getTime()
            val now = System.currentTimeMillis()
            val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
        holder.binding.tvTime.setText("$ago")

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        holder.binding.tvLikeNum.setText(place.likes.size.toString() + " Likes")


        holder.binding.tvViewAllComment.setText("View all "+ place.likes.size.toString() + " comments" )
        try {
            Glide.with(context).load(place.image.url)
                .into(holder.binding.ivPostImage)
        } catch (e: Exception){
            }

        holder.binding.ivGiveLike.setOnClickListener {

            holder.binding.ivGiveLike.visibility = View.GONE
            holder.binding.ivLiked.visibility = View.VISIBLE
        }
        holder.binding.ivLiked.setOnClickListener {
            holder.binding.ivGiveLike.visibility = View.VISIBLE
            holder.binding.ivLiked.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return post.size
    }
}