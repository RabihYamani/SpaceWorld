package com.example.spaceworld

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.spaceworld.databinding.RoverBinding
import com.squareup.picasso.Picasso


class Rover : ConstraintLayout {

    var  mBinding: RoverBinding

    var roverModel: RoverModel? =null
    set(value){
        field= value
        mBinding.rover = value

        renderImage()
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mBinding = DataBindingUtil.inflate(inflater, R.layout.rover, this, true)
    }

    fun renderImage(){
        var imageView: ImageView = findViewById(R.id.imageView3)
        Picasso.get().load(roverModel?.imageUrl).into(imageView);
    }
}

