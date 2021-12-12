package com.example.spaceworld.rover_photos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceworld.PhotosAdapter
import com.example.spaceworld.R
import com.example.spaceworld.models.RoverModel
import com.example.spaceworld.models.RoverPhotoModel
import com.example.spaceworld.repositories.GLOBAL_LIST
import com.example.spaceworld.repositories.RoverRepository
import kotlinx.coroutines.launch


class RoverPhotos : Fragment() {

    var photos: List<RoverPhotoModel>? = ArrayList()
    var rover: RoverModel? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.fragment_rover_photos, container, false)

        val args = RoverPhotosArgs.fromBundle(arguments!!)
        Log.v("abc", args.roverid.toString())

        lifecycleScope.launch {
           // rover = RoverRepository.getRovers()?.get(0)

          //  photos= RoverRepository.getPhotos(rover)
            photos= GLOBAL_LIST


            val rv_photos: RecyclerView = view.findViewById(R.id.rv_photos)

            // set layout manager to grid layout photos in grid
            rv_photos.layoutManager = GridLayoutManager(context,2)
// set adapter

            rv_photos.adapter = PhotosAdapter(photos, context)

        }

        return view
    // Inflate the layout for this fragment





    }

}