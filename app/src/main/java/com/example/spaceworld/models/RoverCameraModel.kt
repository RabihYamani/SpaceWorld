package com.example.spaceworld.models

import com.beust.klaxon.Json

data class RoverCameraModel (
    val id:Int,
    val name: String,

    @Json(name= "full_name")
    val fullName: String,
//    val rover: RoverModel? =null

        )