package com.example.spaceworld.rover_list

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.beust.klaxon.Klaxon
import com.example.spaceworld.MainFragment
import com.example.spaceworld.PhotosViewAdapter
import com.example.spaceworld.R
import com.example.spaceworld.api.MarsApiService
import com.example.spaceworld.databinding.FragmentRoversListBinding
import com.example.spaceworld.models.RoverModel
import com.example.spaceworld.models.RoverPhotoModel
import com.example.spaceworld.repositories.RoverRepository
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.StringReader

class RoversList : Fragment() {

    private lateinit var binding: FragmentRoversListBinding

    private var rovers: List<RoverModel>? = null
    private var curiosityList = ArrayList<RoverPhotoModel>()
    private var spiritList = ArrayList<RoverPhotoModel>()
    private var opportunityList = ArrayList<RoverPhotoModel>()
    private var kpProgressBar: KProgressHUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // rovers = RoverRepository.getRovers()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rovers_list, container, false)


        if (curiosityList.size == 0)
            getCuriosityList()
        else{
            val adapter = PhotosViewAdapter(requireContext(), childFragmentManager)
            adapter.addFragment(MainFragment.newInstance(curiosityList), "")
            adapter.addFragment(MainFragment.newInstance(spiritList), "")
            adapter.addFragment(MainFragment.newInstance(opportunityList), "")

            binding.viewPager.adapter = adapter
        }



        binding.navController = findNavController()

        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)

        binding.screenWidth = displayMetrics.widthPixels
        return binding.root
    }

    private fun getCuriosityList() {
        showProgressBar(true)
        MarsApiService.MarsApi.retrofitService.getRoversCuriosity()
            .enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.v("abc", "Failure" + t.message)
                    showProgressBar(false)

                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.v("abc", "Response" + response.body())
                    Log.v("abc", "isSuccesful" + response.isSuccessful)
                    Log.v("abc", "code: " + response.code())
                    if (response.code() == 200) {
                        val klaxon = Klaxon()
                        val parsed = klaxon.parseJsonObject(StringReader(response.body()))
                        val dataArray = parsed.array<Any>("photos")

                        val curiosList: List<RoverPhotoModel>? = dataArray?.let {
                            klaxon.parseFromJsonArray(it)
                        }

                        curiosityList = curiosList as ArrayList<RoverPhotoModel>
                        Log.e("curiosityListLength ", curiosityList.size.toString())
                        getSpiritList()
                    }
                }
            })
    }

    fun getSpiritList() {
        MarsApiService.MarsApi.retrofitService.getRoversSpirit().enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.v("abc", "Failure" + t.message)
                showProgressBar(false)

            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.v("abc", "Response" + response.body())
                Log.v("abc", "isSuccesful" + response.isSuccessful)
                Log.v("abc", "code: " + response.code())
                if (response.code() == 200) {
                    val klaxon = Klaxon()
                    val parsed = klaxon.parseJsonObject(StringReader(response.body()))
                    val dataArray = parsed.array<Any>("photos")

                    val spirList: List<RoverPhotoModel>? = dataArray?.let {
                        klaxon.parseFromJsonArray(it)
                    }

                    spiritList = spirList as ArrayList<RoverPhotoModel>
                    Log.e("curiosityListLength ", spiritList.size.toString())

                    getOpportunityList()
                }
            }
        })
    }

    fun getOpportunityList() {
        MarsApiService.MarsApi.retrofitService.getRoversOpportunity()
            .enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.v("abc", "Failure" + t.message)
                    showProgressBar(false)

                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.v("abc", "Response" + response.body())
                    Log.v("abc", "isSuccesful" + response.isSuccessful)
                    Log.v("abc", "code: " + response.code())
                    if (response.code() == 200) {
                        val klaxon = Klaxon()
                        val parsed = klaxon.parseJsonObject(StringReader(response.body()))
                        val dataArray = parsed.array<Any>("photos")

                        val opportList: List<RoverPhotoModel>? = dataArray?.let {
                            klaxon.parseFromJsonArray(it)
                        }

                        opportunityList = opportList as ArrayList<RoverPhotoModel>
                        Log.e("curiosityListLength ", opportunityList.size.toString())
                        val adapter = PhotosViewAdapter(requireContext(), childFragmentManager)
                        adapter.addFragment(MainFragment.newInstance(curiosityList), "")
                        adapter.addFragment(MainFragment.newInstance(spiritList), "")
                        adapter.addFragment(MainFragment.newInstance(opportList), "")

                        binding.viewPager.adapter = adapter

                        showProgressBar(false)
                    }
                }
            })
    }

    private fun showProgressBar(isShow: Boolean) {
        if (kpProgressBar == null) {
            kpProgressBar = KProgressHUD.create(requireContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)

        }

        if (isShow)
            kpProgressBar?.show()
        else
            kpProgressBar?.dismiss()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RoversList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RoversList().apply {
                arguments = Bundle().apply {

                }
            }
    }
}