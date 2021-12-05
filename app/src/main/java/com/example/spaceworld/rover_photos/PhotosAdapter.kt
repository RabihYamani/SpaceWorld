package com.example.spaceworld

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceworld.models.RoverPhotoModel
import com.example.spaceworld.rover_photos.RoverPhotosDirections
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

        holder.image.setOnClickListener { view: View ->
            view.findNavController().navigate(RoverPhotosDirections.actionRoverPhotosToPhotoFragment(photo?.id?:0 ))
        }
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