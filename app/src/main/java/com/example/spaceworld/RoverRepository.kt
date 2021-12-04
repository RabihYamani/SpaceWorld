package com.example.spaceworld

import android.util.Log
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import java.io.StringReader

object RoverRepository {

    val roverJSON: String = """{
  "rovers": [
    {
      "id": 5,
      "name": "Curiosity",
      "landing_date": "2012-08-06",
      "launch_date": "2011-11-26",
      "status": "active",
      "max_sol": 2540,
      "max_date": "2019-09-28",
      "total_photos": 366206,
      "cameras": [
        {
          "name": "FHAZ",
          "full_name": "Front Hazard Avoidance Camera"
        },
        {
          "name": "NAVCAM",
          "full_name": "Navigation Camera"
        },
        {
          "name": "MAST",
          "full_name": "Mast Camera"
        },
        {
          "name": "CHEMCAM",
          "full_name": "Chemistry and Camera Complex"
        },
        {
          "name": "MAHLI",
          "full_name": "Mars Hand Lens Imager"
        },
        {
          "name": "MARDI",
          "full_name": "Mars Descent Imager"
        },
        {
          "name": "RHAZ",
          "full_name": "Rear Hazard Avoidance Camera"
        }
      ]
    },
    {
      "id": 7,
      "name": "Spirit",
      "landing_date": "2004-01-04",
      "launch_date": "2003-06-10",
      "status": "complete",
      "max_sol": 2208,
      "max_date": "2010-03-21",
      "total_photos": 124550,
      "cameras": [
        {
          "name": "FHAZ",
          "full_name": "Front Hazard Avoidance Camera"
        },
        {
          "name": "NAVCAM",
          "full_name": "Navigation Camera"
        },
        {
          "name": "PANCAM",
          "full_name": "Panoramic Camera"
        },
        {
          "name": "MINITES",
          "full_name": "Miniature Thermal Emission Spectrometer (Mini-TES)"
        },
        {
          "name": "ENTRY",
          "full_name": "Entry, Descent, and Landing Camera"
        },
        {
          "name": "RHAZ",
          "full_name": "Rear Hazard Avoidance Camera"
        }
      ]
    },
    {
      "id": 6,
      "name": "Opportunity",
      "landing_date": "2004-01-25",
      "launch_date": "2003-07-07",
      "status": "complete",
      "max_sol": 5111,
      "max_date": "2018-06-11",
      "total_photos": 198439,
      "cameras": [
        {
          "name": "FHAZ",
          "full_name": "Front Hazard Avoidance Camera"
        },
        {
          "name": "NAVCAM",
          "full_name": "Navigation Camera"
        },
        {
          "name": "PANCAM",
          "full_name": "Panoramic Camera"
        },
        {
          "name": "MINITES",
          "full_name": "Miniature Thermal Emission Spectrometer (Mini-TES)"
        },
        {
          "name": "ENTRY",
          "full_name": "Entry, Descent, and Landing Camera"
        },
        {
          "name": "RHAZ",
          "full_name": "Rear Hazard Avoidance Camera"
        }
      ]
    }
  ]
}"""

    fun getRovers(): List<RoverModel>? {

        //      TODO read rovers from Json

        val klaxon = Klaxon()
        val parsed = klaxon.parseJsonObject(StringReader(roverJSON))
        val dataArray = parsed.array<Any>("rovers")
//        Log.v("test", "" + dataArray?.toJsonString()) checking if it was reading the full array split

        return dataArray?.let { klaxon.parseFromJsonArray(it) }
    }

    fun getPhotos(rover: RoverModel?): ArrayList<RoverPhotoModel>?{
        val photos: ArrayList<RoverPhotoModel> = ArrayList()
        val camera: RoverCameraModel = RoverCameraModel(
            20,
            "FHAX",
            "Front Hazard Avoidance Camera",
            rover
        )

        photos.add(
            RoverPhotoModel(
            1,
            100,
            "26/11/2020",
             "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG",
                rover,
                camera
        )
        )
        photos.add(
            RoverPhotoModel(
                2,
                200,
                "02/01/2021",
                "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG",
                rover,
                camera
            )
        )
        photos.add(
            RoverPhotoModel(
                3,
                300,
                "23/11/2020",
                "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG",
                rover,
                camera
            )
        )
        photos.add(
            RoverPhotoModel(
                4,
                400,
                "29/11/2020",
                "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG",
                rover,
                camera
            )
        )
        return photos
    }
}
