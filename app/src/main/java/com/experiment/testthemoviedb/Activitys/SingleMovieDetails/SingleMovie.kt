package com.experiment.testthemoviedb.Activitys.SingleMovieDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.experiment.testthemoviedb.Data.api.POSTER_BASE_URL
import com.experiment.testthemoviedb.Data.api.TheMovieDBClient
import com.experiment.testthemoviedb.Data.api.TheMovieDBInterface
import com.experiment.testthemoviedb.Data.models.MovieDetails
import com.experiment.testthemoviedb.Data.repository.NetworkState
import com.experiment.testthemoviedb.R
import kotlinx.android.synthetic.main.activity_single_movie.*
import java.text.NumberFormat
import java.util.*

class SingleMovie : AppCompatActivity() {

    

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository: MovieDetailsRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

        val movieid = intent.getIntExtra("movieid", 1)
        initUI( movieid )
    }

    private fun initUI( movieid: Int){
        val apiService : TheMovieDBInterface = TheMovieDBClient.getClient()
        movieRepository =
            MovieDetailsRepository(
                apiService
            )

        viewModel = getViewModel( movieid )
        viewModel.movieDetails.observe( this, Observer {
            bindUI( it )
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if( it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if( it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })
    }

    private fun bindUI(it: MovieDetails) {
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_data_year.text = it.releaseDate
        movie_rating.text = it.rating.toString()
        movie_runtime.text = it.runtime.toString() + " minutes"
        movie_overview.text = it.overview

        val formatCurrency: NumberFormat = NumberFormat.getCurrencyInstance( Locale.US )
        movie_budget.text = formatCurrency.format(it.budget)
        movie_revenue.text = formatCurrency.format(it.revenue)

        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(img_movie_poster)
    }


    private fun getViewModel( movieid:Int ): SingleMovieViewModel {

        return ViewModelProviders.of(this, object : ViewModelProvider.Factory{

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleMovieViewModel(
                    movieRepository,
                    movieid
                ) as T
            }

        })[SingleMovieViewModel::class.java]
    }
}
