package com.experiment.testthemoviedb.Activitys.SingleMovieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.experiment.testthemoviedb.Data.models.MovieDetails
import com.experiment.testthemoviedb.Data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel(private val movieRepository: MovieDetailsRepository, movieid: Int) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movieDetails: LiveData<MovieDetails> by lazy {
        movieRepository.fetchSingleMovieDetails( compositeDisposable, movieid)
    }

    val networkState : LiveData<NetworkState> by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }

    //Limpiamos todo: cuando la actividad o fragmento es destruido
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}