package com.example.spaceworld

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.spaceworld.databinding.FragmentRoversListBinding

class RoversList : Fragment() {

        private lateinit var binding: FragmentRoversListBinding

        private  var rovers: List<RoverModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rovers =  RoverRepository.getRovers()
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_rovers_list , container , false)

        if(!rovers.isNullOrEmpty()){
            binding.rover1= rovers?.get(0)
            binding.rover2= rovers?.get(1)
            binding.rover3= rovers?.get(2)
        }


        val displayMetrics = DisplayMetrics()
    activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)

        binding.screenWidth= displayMetrics.widthPixels
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
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