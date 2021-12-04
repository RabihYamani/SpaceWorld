package com.example.spaceworld.rover_list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.spaceworld.R
import com.example.spaceworld.databinding.RoverBinding
import com.example.spaceworld.models.RoverModel
import com.squareup.picasso.Picasso


class Rover : ConstraintLayout {

    var  mBinding: RoverBinding

    var roverModel: RoverModel? =null
    set(value){
        field= value
        mBinding.rover = value

        renderImage()
    }

    var screenWidth: Int= 300
    set(value){
        field = value
        var root: View = findViewById<ConstraintLayout>(R.id.root)
        val layoutParams= root.getLayoutParams()
        layoutParams.width = value
        root.setLayoutParams(layoutParams)

    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mBinding = DataBindingUtil.inflate(inflater, R.layout.rover, this, true)

        mBinding.button.setOnClickListener { view: View ->
            view.findNavController().navigate(RoversListDirections.actionRoversListToRoverPhotos(roverModel?.id?:0))
        }
    }

    fun renderImage(){
        val images: HashMap<Int, String> = HashMap()
        images.put(5, "https://www.science.org/do/10.1126/science.aan7004/abs/sn-curiosity.jpg")
        images.put(7, "https://i.guim.co.uk/img/media/545dcd3ba148179e4f7724a7600e55b29bac07f1/0_150_2000_1200/master/2000.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=573b63b10bdca845c21f1963338940af")
        images.put(6, "https://www.mercurynews.com/wp-content/uploads/2016/08/20111126_023011_marsrover600.jpg?w=640")

        var imageView: ImageView = findViewById(R.id.imageView3)
        Picasso.get().load(images.get(roverModel?.id)).into(imageView);
    }
}

