package com.example.spaceworld

data class RoverPhotoModel (
    val id: Int,
    val sol: Int,
    val date: String,
    val imgScr: String,
    val rover: RoverModel?,
    val camera: RoverCameraModel
        )
