package com.example.spaceworld.photo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.spaceworld.R
import com.example.spaceworld.databinding.FragmentPhotoBinding
import com.example.spaceworld.repositories.GLOBAL_LIST
import com.example.spaceworld.repositories.RoverRepository
import com.example.spaceworld.rover_photos.RoverPhotosArgs
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 * Use the [PhotoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhotoFragment : Fragment() {

    private lateinit var binding: FragmentPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_photo, container , false)

        val args = PhotoFragmentArgs.fromBundle(arguments!!)

       // val photo = RoverRepository.getPhoto(args.photoId)

        val photo = GLOBAL_LIST.findLast { it.id == args.photoId }
        binding.camera.text= photo?.camera?.name
        binding.date.text = photo?.date
        val picasso= Picasso.get()
        picasso.load(photo?.imgScr?.replace("http", "https")).into(binding.image)


        return binding.root
    }


}