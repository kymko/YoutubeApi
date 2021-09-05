package com.example.youtubeapi.ui.playlist.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapi.databinding.ListItemBinding
import com.example.youtubeapi.extensions.load
import com.example.youtubeapi.model.PlayList
import com.example.youtubeapi.model.PlayListItems
import kotlinx.coroutines.withContext

class PlayListAdapter() : RecyclerView.Adapter<PlayListAdapter.ViewHolder>() {

    private lateinit var binding: ListItemBinding
    private val playList:ArrayList<PlayListItems> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(playList[position])
    }

    override fun getItemCount(): Int {
        return playList.size
    }
    fun addPlayList(list: ArrayList<PlayListItems>?){
        if (list != null) {
            playList.addAll(list)
        }
        notifyDataSetChanged()

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding:ListItemBinding = ListItemBinding.bind(itemView)

        fun onBind(playList: PlayListItems?) {
            Log.d("tag","titleSnip: ${playList?.snippet?.title}")
            binding.tvHeader.text = playList?.snippet?.title
            Glide.with(itemView)
                .load(playList?.snippet?.thumbnails?.default?.url.toString())
                .centerCrop()
                .into(binding.imageView)

        }
    }
}