package com.experiment.testthemoviedb.Activitys.SingleMovieDetails

import androidx.lifecycle.LiveData
import com.experiment.testthemoviedb.Data.api.TheMovieDBInterface
import com.experiment.testthemoviedb.Data.models.MovieDetails
import com.experiment.testthemoviedb.Data.repository.MovieDetailsNetworkDataSource
import com.experiment.testthemoviedb.Data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository (private val apiService: TheMovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails( compositeDisposable: CompositeDisposable, movieid: Int ) : LiveData<MovieDetails> {

        this.movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource( apiService, compositeDisposable )
        this.movieDetailsNetworkDataSource.fetchMovieDetails( movieid )

        return movieDetailsNetworkDataSource.downloadMovieDetailsResponse
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}