package com.example.spaceworld

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RoverPhotos : Fragment() {

    var photos: ArrayList<RoverPhotoModel>? = ArrayList()
    var rover: RoverModel? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.fragment_rover_photos, container, false)
        rover= RoverRepository.getRovers()?.get(0)

        photos= RoverRepository.getPhotos(rover)

        var rv_photos: RecyclerView = view.findViewById(R.id.rv_photos)

        // set layout manager to grid layout photos in grid
        rv_photos.layoutManager = GridLayoutManager(context,3)
// set adapter

        rv_photos.adapter = PhotosAdapter(photos, context)

        return view
    // Inflate the layout for this fragment





    }

}