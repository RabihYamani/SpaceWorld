package com.example.spaceworld

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.spaceworld.models.RoverPhotoModel
import com.example.spaceworld.repositories.GLOBAL_LIST
import com.example.spaceworld.rover_list.RoversListDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainFragment : Fragment() {
    var roversData = ArrayList<RoverPhotoModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    companion object {
        fun newInstance(photosListRovers: ArrayList<RoverPhotoModel>) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    roversData = photosListRovers
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("333333", roversData.size.toString())

        if (roversData.size > 0) {
            val roverModel = roversData.get(0)
            Picasso.get().load(roverModel.imgScr).into(view.imageView3)
            view.name.text = roverModel.rover.name
            view.status.text = roverModel.rover.status
            view.launchDate.text = roverModel.rover.launchDate
            view.landingDate.text = roverModel.rover.landingDate
            view.maxDate.text = roverModel.earth_date
            view.button.text = "View Photo  (" + roversData.size.toString() + ")"

            view.button.setOnClickListener {
                GLOBAL_LIST = roversData
                lifecycleScope.launch {
                    delay(500)
                    findNavController().navigate(RoversListDirections.actionRoversListToRoverPhotos(0))
                }
            }

        }
    }
}