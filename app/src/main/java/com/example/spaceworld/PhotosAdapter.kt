package com.example.spaceworld

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rover_photo_item.view.*

class PhotosAdapter(val photos: List<RoverPhotoModel>?,
                    val context: Context?) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rover_photo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var photo: RoverPhotoModel? = photos?.get(position)

        holder.camera.text= photo?.camera?.name
        holder.date.text = photo?.date
        val picasso= Picasso.get()
        picasso.load(photo?.imgScr?.replace("http", "https")).into(holder.image)
    }

    override fun getItemCount(): Int {
        return photos?.size?:0
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val image = view.image
    val camera = view.camera
    val date = view.date
}