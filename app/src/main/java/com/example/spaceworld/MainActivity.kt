package com.example.spaceworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.spaceworld.models.RoverModel


class MainActivity : AppCompatActivity(), FragmentNavigation {

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



        supportFragmentManager.beginTransaction()
            .add(R.id.navHostFragment, LoginFragment())
            .commit()
    }

    override fun navigateFrag(fragment: Fragment, addToStack: Boolean) {
       val transaction = supportFragmentManager
           .beginTransaction()
           .replace(R.id.navHostFragment, fragment)

        if(addToStack){
            transaction.addToBackStack(null)
        }
        transaction.commit()

    }

}

