package com.rubenmimoun.coroutinemovieexample

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rubenmimoun.coroutinemovieexample.model.Movie

class AdapterImages(val list: List<Movie>, val context: Context) : RecyclerView.Adapter<AdapterImages.BitmapViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterImages.BitmapViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item,parent, false)
        return BitmapViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AdapterImages.BitmapViewHolder, position: Int) {
        val movie =  list[position]
        Log.i("size", list.size.toString() )

        holder.bind(movie)
    }

    inner class BitmapViewHolder(itemView: View) :  RecyclerView.ViewHolder(itemView){

        private var image : ImageView? = null
        init {
            image = itemView.findViewById(R.id.bitmap_img)
        }

        fun bind(movie: Movie){
            Glide.with(context).load(movie.image).into(image!!)
        }

    }

}