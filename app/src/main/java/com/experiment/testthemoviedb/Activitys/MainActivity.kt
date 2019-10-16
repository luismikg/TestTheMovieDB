package com.experiment.testthemoviedb.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.experiment.testthemoviedb.Activitys.SingleMovieDetails.SingleMovie
import com.experiment.testthemoviedb.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    fun initUI(){

        btn.setOnClickListener{
            val intent = Intent( this, SingleMovie::class.java )
            intent.putExtra("movieid", 301528)
            this.startActivity( intent )
        }
    }
}

