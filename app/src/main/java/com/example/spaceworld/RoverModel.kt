package com.example.spaceworld

data class RoverModel (
    val id: Int,
    val name: String,
    val launchDate: String= "12/12/12",
    val landingDate: String = "12/12/12",
    val maxDate: String = "12/12/12",
    val status: String = "active",
    val maxSol: Int =0,
    val totalPhotos: Int =0,
    val imageUrl: String =""
)