package com.example.spaceworld

import com.beust.klaxon.Json

data class RoverModel (
    val id: Int,
    val name: String,
    @Json(name= "launch_date")
    val launchDate: String= "12/12/12",

    @Json(name= "landing_date")
    val landingDate: String = "12/12/12",

    @Json(name= "max_date")
    val maxDate: String = "12/12/12",

    val status: String = "active",

    @Json(name= "max_sol")
    val maxSol: Int =0,

    @Json(name= "total_photos")
    val totalPhotos: Int =0,

    val imageUrl: String =""
)