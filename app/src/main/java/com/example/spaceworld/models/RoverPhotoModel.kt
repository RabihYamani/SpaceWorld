package com.example.spaceworld.models

import com.beust.klaxon.Json
import com.example.spaceworld.models.RoverCameraModel

data class RoverPhotoModel (
    val id: Int,
    val sol: Int,
    @Json(name= "earth_date")
    val date: String,
    @Json(name= "img_src")
    val imgScr: String,
//    val rover: RoverModel?,
    val camera: RoverCameraModel,
    val rover: RoverModel,
    val earth_date : String
        )
