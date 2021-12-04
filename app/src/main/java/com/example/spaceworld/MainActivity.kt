package com.example.spaceworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spaceworld.models.RoverModel


class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding

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
        setContentView(R.layout.activity_main)




    }

}

