package com.example.spaceworld.repositories


import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.spaceworld.api.MarsApiService
import com.example.spaceworld.models.RoverModel
import com.example.spaceworld.models.RoverPhotoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    val roverPhotos: String = """
{"photos":[{"id":102693,"sol":1000,"camera":{"id":20,"name":"FHAZ","rover_id":5,"full_name":"Front Hazard Avoidance Camera"},"img_src":"http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG","earth_date":"2015-05-30","rover":{"id":5,"name":"Curiosity","landing_date":"2012-08-06","launch_date":"2011-11-26","status":"active"}},{"id":102694,"sol":1000,"camera":{"id":20,"name":"FHAZ","rover_id":5,"full_name":"Front Hazard Avoidance Camera"},"img_src":"http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FRB_486265257EDR_F0481570FHAZ00323M_.JPG","earth_date":"2015-05-30","rover":{"id":5,"name":"Curiosity","landing_date":"2012-08-06","launch_date":"2011-11-26","status":"active"}},{"id":102850,"sol":1000,"camera":{"id":21,"name":"RHAZ","rover_id":5,"full_name":"Rear Hazard Avoidance Camera"},"img_src":"http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/rcam/RLB_486265291EDR_F0481570RHAZ00323M_.JPG","earth_date":"2015-05-30","rover":{"id":5,"name":"Curiosity","landing_date":"2012-08-06","launch_date":"2011-11-26","status":"active"}},{"id":102851,"sol":1000,"camera":{"id":21,"name":"RHAZ","rover_id":5,"full_name":"Rear Hazard Avoidance Camera"},"img_src":"http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/rcam/RRB_486265291EDR_F0481570RHAZ00323M_.JPG","earth_date":"2015-05-30","rover":{"id":5,"name":"Curiosity","landing_date":"2012-08-06","launch_date":"2011-11-26","status":"active"}},{"id":424905,"sol":1000,"camera":{"id":22,"name":"MAST","rover_id":5,"full_name":"Mast Camera"},"img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631300503690E01_DXXX.jpg","earth_date":"2015-05-30","rover":{"id":5,"name":"Curiosity","landing_date":"2012-08-06","launch_date":"2011-11-26","status":"active"}},{"id":424906,"sol":1000,"camera":{"id":22,"name":"MAST","rover_id":5,"full_name":"Mast Camera"},"img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631300305227E03_DXXX.jpg","earth_date":"2015-05-30","rover":{"id":5,"name":"Curiosity","landing_date":"2012-08-06","launch_date":"2011-11-26","status":"active"}},{"id":424907,"sol":1000,"camera":{"id":22,"name":"MAST","rover_id":5,"full_name":"Mast Camera"},"img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631290503689E01_DXXX.jpg","earth_date":"2015-05-30","rover":{"id":5,"name":"Curiosity","landing_date":"2012-08-06","launch_date":"2011-11-26","status":"active"}},{"id":424908,"sol":1000,"camera":{"id":22,"name":"MAST","rover_id":5,"full_name":"Mast Camera"},"img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631290305226E03_DXXX.jpg","earth_date":"2015-05-30","rover":{"id":5,"name":"Curiosity","landing_date":"2012-08-06","launch_date":"2011-11-26","status":"active"}},{"id":424909,"sol":1000,"camera":{"id":22,"name":"MAST","rover_id":5,"full_name":"Mast Camera"},"img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631280503688E0B_DXXX.jpg","earth_date":"2015-05-30","rover":{"id":5,"name":"Curiosity","landing_date":"2012-08-06","launch_date":"2011-11-26","status":"active"}}]}    
""".trimIndent()

    fun getRovers(): List<RoverModel>? {

        //  Read rovers from Json

//        val klaxon = Klaxon()
//        val parsed = klaxon.parseJsonObject(StringReader(roverJSON))
//        val dataArray = parsed.array<Any>("rovers")
////        Log.v("test", "" + dataArray?.toJsonString()) checking if it was reading the full array split
//
//        return dataArray?.let { klaxon.parseFromJsonArray(it) }
        MarsApiService.MarsApi.retrofitService.getRovers().enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t:Throwable){
                Log.v("abc", "Failure" +t.message)
            }
            override fun onResponse(call: Call<String>, response: Response<String>){
                Log.v("abc", "Response" + response.body())
                Log.v("abc", "isSuccesful" + response.isSuccessful)
                Log.v("abc", "code: " + response.code())
            }
            })
                val klaxon = Klaxon()
        val parsed = klaxon.parseJsonObject(StringReader(roverJSON))
        val dataArray = parsed.array<Any>("rovers")
//        Log.v("test", "" + dataArray?.toJsonString()) checking if it was reading the full array split

        return dataArray?.let { klaxon.parseFromJsonArray(it) }
    }

    fun getPhotos(rover: RoverModel?): List<RoverPhotoModel>?{
        val photos: ArrayList<RoverPhotoModel> = ArrayList()


        val klaxon = Klaxon()
        val parsed = klaxon.parseJsonObject(StringReader(roverPhotos))
        val dataArray = parsed.array<Any>("photos")

        return dataArray?.let { klaxon.parseFromJsonArray(it) }
    }

    fun getPhoto(photoId: Int): RoverPhotoModel? {
        return getPhotos(null)?.get(0)
    }
}
