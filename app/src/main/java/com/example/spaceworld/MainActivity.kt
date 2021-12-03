package com.example.spaceworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.spaceworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val curiosity = RoverModel(
        1,
        "curiosity test",
        "4/4/2004",
        "5/5/2006",
        "1/1/2011",
        "complete",
        100,
        100,
        "https://www.science.org/do/10.1126/science.aan7004/abs/sn-curiosity.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.roverCuriosityModel= curiosity
    }
}

