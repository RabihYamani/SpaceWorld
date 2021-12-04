package com.example.spaceworld

import com.beust.klaxon.Json

data class RoverPhotoModel (
    val id: Int,
    val sol: Int,
    @Json(name= "earth_date")
    val date: String,
    @Json(name= "img_src")
    val imgScr: String,
//    val rover: RoverModel?,
    val camera: RoverCameraModel
        )
